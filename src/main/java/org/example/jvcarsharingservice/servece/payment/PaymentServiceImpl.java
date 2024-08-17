package org.example.jvcarsharingservice.servece.payment;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.payment.CreatePaymentRequestDto;
import org.example.jvcarsharingservice.dto.payment.PaymentDto;
import org.example.jvcarsharingservice.mapper.PaymentMapper;
import org.example.jvcarsharingservice.model.classes.Car;
import org.example.jvcarsharingservice.model.classes.Payment;
import org.example.jvcarsharingservice.model.classes.Rental;
import org.example.jvcarsharingservice.model.classes.User;
import org.example.jvcarsharingservice.model.enums.PaymentType;
import org.example.jvcarsharingservice.model.enums.Status;
import org.example.jvcarsharingservice.repository.car.CarRepository;
import org.example.jvcarsharingservice.repository.payment.PaymentRepository;
import org.example.jvcarsharingservice.repository.rental.RentalRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final static String sessionUrl = "asdasd";
    private final static String sessionId = "12345";

    @Override
    public PaymentDto createPayment(User user, CreatePaymentRequestDto requestDto) {
        Rental rental = rentalRepository.findById(requestDto.rentalId()).orElseThrow(
                () -> new EntityNotFoundException(
                        "Rental with id " + requestDto.rentalId() + " not found")
        );
        BigDecimal total = getAmountToPay(rental);
        PaymentType payment = PaymentType.PAYMENT;
        Status pending = Status.PENDING;
        Payment paymentEntity = getPayment(pending, payment, rental, total);

        return paymentMapper.toDto(
                paymentRepository.save(paymentEntity));
    }

    private BigDecimal getAmountToPay(Rental rental) {
        Car car = carRepository.findById(rental.getCarId()).orElseThrow(
                () -> new EntityNotFoundException("Car with id " + rental.getCarId() + " not found")
        );
        BigDecimal dailyFee = car.getDailyFee();
        long start = rental.getRentalDate().toEpochDay();
        long end = rental.getReturnDate().toEpochDay();
        long daysAmount = end - start;
        BigDecimal total = dailyFee.multiply(BigDecimal.valueOf(daysAmount));
        return total;
    }

    private Payment getPayment(Status pending, PaymentType payment, Rental rental, BigDecimal total) {
        Payment paymentEntity = new Payment();
        paymentEntity.setStatus(pending);
        paymentEntity.setType(payment);
        paymentEntity.setRentalId(rental.getId());
        paymentEntity.setSessionUrl(sessionUrl);
        paymentEntity.setSessionId(sessionId);
        paymentEntity.setAmountToPay(total);
        paymentEntity.setDeleted(false);
        return paymentEntity;
    }

    @Override
    public PaymentDto checkPaymentSuccess(User user, String sessionId) {
        return null;
    }

    @Override
    public PaymentDto pausePayment(User user, String sessionId) {
        return null;
    }
}
