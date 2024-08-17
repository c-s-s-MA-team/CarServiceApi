package org.example.jvcarsharingservice.repository;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {
    String getKey();

    Specification<T> getSpecification(String[] params);

    Specification<T> getSpecification(Long[] params);

    Specification<T> getSpecification(Boolean params);
}
