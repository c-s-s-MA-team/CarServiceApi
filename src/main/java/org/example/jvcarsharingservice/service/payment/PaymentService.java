package org.example.jvcarsharingservice.service.payment;

import java.util.List;
import org.example.jvcarsharingservice.dto.payment.CreatePaymentRequestDto;
import org.example.jvcarsharingservice.dto.payment.PaymentDto;
import org.example.jvcarsharingservice.dto.payment.PaymentSearchParameters;
import org.example.jvcarsharingservice.model.classes.User;

public interface PaymentService {
    PaymentDto createPayment(User user, CreatePaymentRequestDto requestDto);

    String checkPaymentSuccess(String sessionId);

    String pausePayment(String sessionId);

    List<PaymentDto> getPayments(PaymentSearchParameters searchParameters);
}
