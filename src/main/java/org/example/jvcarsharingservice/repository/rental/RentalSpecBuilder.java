package org.example.jvcarsharingservice.repository.rental;

import org.example.jvcarsharingservice.dto.rental.RentalSearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface RentalSpecBuilder<T> {
    Specification<T> build(RentalSearchParameters searchParameters);
}
