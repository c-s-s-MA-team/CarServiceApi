package org.example.jvcarsharingservice.service.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.payment.CreatePaymentRequestDto;
import org.example.jvcarsharingservice.dto.payment.PaymentDto;
import org.example.jvcarsharingservice.dto.payment.PaymentSearchParameters;
import org.example.jvcarsharingservice.exception.PaymentException;
import org.example.jvcarsharingservice.mapper.PaymentMapper;
import org.example.jvcarsharingservice.model.classes.Car;
import org.example.jvcarsharingservice.model.classes.Payment;
import org.example.jvcarsharingservice.model.classes.Rental;
import org.example.jvcarsharingservice.model.classes.User;
import org.example.jvcarsharingservice.model.enums.PaymentType;
import org.example.jvcarsharingservice.model.enums.Status;
import org.example.jvcarsharingservice.repository.car.CarRepository;
import org.example.jvcarsharingservice.repository.payment.PaymentRepository;
import org.example.jvcarsharingservice.repository.payment.provider.PaymentPaymentSpecificationBuilder;
import org.example.jvcarsharingservice.repository.rental.RentalRepository;
import org.example.jvcarsharingservice.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private static final String paymentDomain = "/payments/";
    public static final String COMPLETE = "complete";
    public static final String OPEN = "open";
    @Value("${domain}")
    private String domain;
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationService notificationService;
    private final PaymentPaymentSpecificationBuilder paymentSpecificationBuilder;

    @Override
    @Transactional
    public PaymentDto createPayment(User user, CreatePaymentRequestDto requestDto) {
        Rental rental = rentalRepository.findById(requestDto.rentalId()).orElseThrow(
                () -> new EntityNotFoundException(
                        "Rental with id " + requestDto.rentalId() + " not found")
        );
        BigDecimal total = getAmountToPay(rental);

        try {
            Session session = createStripeSession(total, rental);
            Payment payment = getPayment(Status.PENDING,
                    PaymentType.PAYMENT,
                    rental,
                    total,
                    session);

            return paymentMapper.toDto(
                    paymentRepository.save(payment));

        } catch (StripeException e) {
            throw new PaymentException("cannot create payment", e);
        }
    }

    private Session createStripeSession(BigDecimal total, Rental rental) throws StripeException {
        SessionCreateParams rentInUsd = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(domain + paymentDomain + "success/{CHECKOUT_SESSION_ID}")
                .setCancelUrl(domain + paymentDomain + "cancel/{CHECKOUT_SESSION_ID}")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount(total.multiply(BigDecimal.valueOf(100)).longValue())
                                .setProductData(
                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Payment for: " + rental.getId())
                                        .build())
                                .build())
                        .setQuantity(1L)
                        .build())
                .setClientReferenceId(rental.getId().toString())
                .build();
        return Session.create(rentInUsd);
    }

    private BigDecimal getAmountToPay(Rental rental) {
        Car car = carRepository.findById(rental.getCarId()).orElseThrow(
                () -> new EntityNotFoundException("Car with id " + rental.getCarId() + " not found")
        );
        BigDecimal dailyFee = car.getDailyFee();
        long start = rental.getRentalDate().toEpochDay();
        long end = rental.getReturnDate().toEpochDay();
        long daysAmount = end - start;
        return dailyFee.multiply(BigDecimal.valueOf(daysAmount));
    }

    private Payment getPayment(Status pending,
                               PaymentType payment,
                               Rental rental,
                               BigDecimal total,
                               Session session) {
        Payment paymentEntity = new Payment();
        paymentEntity.setStatus(pending);
        paymentEntity.setType(payment);
        paymentEntity.setRentalId(rental.getId());
        paymentEntity.setSessionUrl(session.getUrl());
        paymentEntity.setSessionId(session.getId());
        paymentEntity.setAmountToPay(total);
        paymentEntity.setDeleted(false);
        return paymentEntity;
    }

    @Override
    @Transactional
    public String checkPaymentSuccess(String sessionId) {
        try {
            Session session = Session.retrieve(sessionId);
            if (COMPLETE.equals(session.getStatus())) {
                Payment payment = paymentRepository.findBySessionId(sessionId).orElseThrow(
                        () -> new PaymentException("Cannot find payment with session id "
                                + sessionId)
                );
                payment.setStatus(Status.PAID);
                paymentRepository.save(payment);
                String message = "Payment successful! Thank you for your payment.";
                notificationService.notifySuccessfulPayments(message);
                return message;
            } else {
                return "Payment is not completed yet. Please try again later. In Success";
            }
        } catch (StripeException e) {
            throw new PaymentException("cannot retrieve payment success", e);
        }
    }

    @Override
    @Transactional
    public String pausePayment(String sessionId) {
        try {
            Session session = Session.retrieve(sessionId);
            if (OPEN.equals(session.getStatus())) {
                Payment payment = paymentRepository.findBySessionId(sessionId).orElseThrow(
                        () -> new PaymentException("Cannot find payment with session id "
                                + sessionId)
                );
                payment.setStatus(Status.PAUSED);
                paymentRepository.save(payment);
                return "Payment paused. You can resume your payment later.";
            } else if ("complete".equals(session.getStatus())) {
                return "Payment has been paid. Thank you for your payment.";
            } else {
                return "Payment is not completed yet. Please try again later. In Pause";
            }
        } catch (StripeException e) {
            throw new PaymentException("cannot retrieve payment success", e);
        }
    }

    @Override
    public List<PaymentDto> getPayments(PaymentSearchParameters searchParameters) {
        Specification<Payment> build = paymentSpecificationBuilder.build(searchParameters);
        return paymentRepository.findAll(build).stream()
                .map(paymentMapper::toDto)
                .toList();
    }
}
