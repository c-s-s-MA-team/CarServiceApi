package org.example.jvcarsharingservice.repository;

import org.example.jvcarsharingservice.dto.rental.RentalSearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(RentalSearchParameters searchParametres);
}
