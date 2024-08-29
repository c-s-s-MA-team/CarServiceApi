package org.example.jvcarsharingservice.repository.rental.provider;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.rental.RentalSearchParameters;
import org.example.jvcarsharingservice.model.classes.Rental;
import org.example.jvcarsharingservice.repository.SpecificationProviderManager;
import org.example.jvcarsharingservice.repository.rental.RentalSpecBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalSpecificationBuilder implements RentalSpecBuilder<Rental> {

    private final SpecificationProviderManager<Rental> specificationProviderManager;

    @Override
    public Specification<Rental> build(RentalSearchParameters searchParameters) {
        Specification<Rental> spec = Specification.where(null);
        if (searchParameters.userId() != null
                && searchParameters.userId().length > 0) {
            Long[] userIds = Arrays.stream(searchParameters.userId())
                    .map(s -> s.replaceAll("[\\[\\]\"]", ""))
                    .map(Long::valueOf)
                    .toArray(Long[]::new);
            spec = spec.and(
                    specificationProviderManager.getSpecificationProvider("userId")
                            .getSpecification(userIds));
        }
        if (searchParameters.isActive() != null) {
            spec = spec.and(
                    specificationProviderManager.getSpecificationProvider("isActive")
                            .getSpecification(searchParameters.isActive()));
        }
        return spec;
    }
}
