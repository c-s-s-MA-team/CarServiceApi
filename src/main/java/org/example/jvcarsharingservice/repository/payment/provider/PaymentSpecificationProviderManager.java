package org.example.jvcarsharingservice.repository.payment.provider;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.exception.NoProviderException;
import org.example.jvcarsharingservice.model.classes.Payment;
import org.example.jvcarsharingservice.repository.SpecificationProvider;
import org.example.jvcarsharingservice.repository.SpecificationProviderManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSpecificationProviderManager implements SpecificationProviderManager<Payment> {
    private final List<SpecificationProvider<Payment>> specificationProviders;

    @Override
    public SpecificationProvider<Payment> getSpecificationProvider(String key) {
        return specificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(
                        () -> new NoProviderException(
                                "No payment specification provider found for key: " + key
                        )
                );
    }
}
