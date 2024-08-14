package org.example.jvcarsharingservice.repository.rental.provider.specification;

import java.util.Arrays;
import org.example.jvcarsharingservice.model.classes.Rental;
import org.example.jvcarsharingservice.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserIdSpecificationProvider implements SpecificationProvider<Rental> {

    @Override
    public String getKey() {
        return "userId";
    }

    public Specification<Rental> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root
                .get("userId").in(
                        Arrays.stream(params).toArray());
    }
}
