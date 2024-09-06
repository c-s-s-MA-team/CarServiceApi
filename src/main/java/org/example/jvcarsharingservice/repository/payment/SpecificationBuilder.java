package org.example.jvcarsharingservice.repository.payment;

import org.example.jvcarsharingservice.dto.payment.PaymentSearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(PaymentSearchParameters searchParameters);
}
