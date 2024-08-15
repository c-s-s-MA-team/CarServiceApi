package org.example.jvcarsharingservice.repository.rental.provider;

import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.rental.RentalSearchParameters;
import org.example.jvcarsharingservice.model.classes.Rental;
import org.example.jvcarsharingservice.repository.SpecificationBuilder;
import org.example.jvcarsharingservice.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalSpecificationBuilder implements SpecificationBuilder<Rental> {
    private final SpecificationProviderManager<Rental> specificationProviderManager;

    @Override
    public Specification<Rental> build(RentalSearchParameters searchParameters) {
        Specification<Rental> spec = Specification.where(null);
        if (searchParameters.userId() != null
                && searchParameters.userId().length > 0) {
            spec = spec.and(
                    specificationProviderManager.getSpecificationProvider("userId")
                            .getSpecification(searchParameters.userId()));
        }
        return spec;
    }
}
