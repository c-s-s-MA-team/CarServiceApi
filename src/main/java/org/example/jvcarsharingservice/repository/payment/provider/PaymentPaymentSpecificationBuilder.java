package org.example.jvcarsharingservice.repository.payment.provider;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.payment.PaymentSearchParameters;
import org.example.jvcarsharingservice.dto.rental.RentalSearchParameters;
import org.example.jvcarsharingservice.model.classes.Payment;
import org.example.jvcarsharingservice.repository.SpecificationProviderManager;
import org.example.jvcarsharingservice.repository.payment.PaymentSpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPaymentSpecificationBuilder implements PaymentSpecificationBuilder<Payment> {
    private final SpecificationProviderManager<Payment> specificationProviderManager;

    @Override
    public Specification<Payment> build(PaymentSearchParameters searchParameters) {
        Specification<Payment> spec = Specification.where(null);
        if (searchParameters.usersId() != null
                    && searchParameters.usersId().length > 0) {
            Long[] usersIds = Arrays.stream(searchParameters.usersId())
                    .map(s -> s.replaceAll("[\\[\\]\"]", ""))
                    .map(Long::valueOf)
                    .toArray(Long[]::new);
            spec = spec.and(
                    specificationProviderManager.getSpecificationProvider("usersId")
                            .getSpecification(usersIds));
        }
        return spec;
    }
}
