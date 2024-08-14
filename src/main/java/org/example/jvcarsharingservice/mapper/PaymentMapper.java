package org.example.jvcarsharingservice.mapper;

import org.example.jvcarsharingservice.config.MapperConfig;
import org.example.jvcarsharingservice.dto.payment.CreatePaymentRequestDto;
import org.example.jvcarsharingservice.dto.payment.PaymentDto;
import org.example.jvcarsharingservice.model.classes.Payment;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface PaymentMapper {

    PaymentDto toDto(Payment payment);

    Payment toEntity(CreatePaymentRequestDto paymentDto);
}

