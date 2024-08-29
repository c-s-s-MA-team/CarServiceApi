package org.example.jvcarsharingservice.repository.payment;

import org.example.jvcarsharingservice.dto.payment.PaymentSearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface PaymentSpecificationBuilder<T> {

    Specification<T> build(PaymentSearchParameters searchParameters);
}
