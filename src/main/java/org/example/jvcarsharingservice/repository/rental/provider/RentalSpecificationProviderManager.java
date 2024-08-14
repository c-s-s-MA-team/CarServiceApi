package org.example.jvcarsharingservice.repository.rental.provider;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.exception.NoProviderException;
import org.example.jvcarsharingservice.model.classes.Rental;
import org.example.jvcarsharingservice.repository.SpecificationProvider;
import org.example.jvcarsharingservice.repository.SpecificationProviderManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalSpecificationProviderManager implements SpecificationProviderManager<Rental> {
    private final List<SpecificationProvider<Rental>> specificationProviders;

    @Override
    public SpecificationProvider<Rental> getSpecificationProvider(String key) {
        return specificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(
                        () -> new NoProviderException(
                                "No specification provider found for key: " + key)
                );
    }
}
