package org.example.jvcarsharingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.payment.CreatePaymentRequestDto;
import org.example.jvcarsharingservice.dto.payment.PaymentDto;
import org.example.jvcarsharingservice.servece.payment.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentsController {
    private final PaymentService paymentService;

    @Operation(summary = "Get payments by user ID")
    @GetMapping("/?user_id={userId}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentDto getPayments(@PathVariable Long userId) {
        //return paymentService.getPayments(userId);
        return null;
    }

    @Operation(summary = "Create payment session")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDto createPaymentSession(
            @RequestBody @Valid CreatePaymentRequestDto requestDto) {
        //return paymentService.createPayment();
        return null;
    }

    @Operation(summary = "Check successful Stripe payments")
    @GetMapping("/success/{sessionId}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentDto checkPaymentSuccess(@PathVariable String sessionId) {
        //return paymentService.checkPaymentSuccess(sessionId);
        return null;
    }

    @Operation(summary = "Return payment paused message")
    @GetMapping("/cancel/{sessionId}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentDto paymentPaused(@PathVariable String sessionId) {
        //return paymentService.pausePayment(sessionId);
        return null;
    }
}

