package org.example.jvcarsharingservice.servece.payment;

import org.example.jvcarsharingservice.dto.payment.CreatePaymentRequestDto;
import org.example.jvcarsharingservice.dto.payment.PaymentDto;
import org.example.jvcarsharingservice.dto.payment.PaymentSearchParameters;
import org.example.jvcarsharingservice.model.classes.User;

import java.util.List;

public interface PaymentService {
    PaymentDto createPayment(User user, CreatePaymentRequestDto requestDto);

    String checkPaymentSuccess(String sessionId);

    String pausePayment(String sessionId);

    List<PaymentDto> getPayments(PaymentSearchParameters searchParameters);
}
