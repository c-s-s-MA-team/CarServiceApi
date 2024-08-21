package org.example.jvcarsharingservice.repository.payment.provider.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.example.jvcarsharingservice.model.classes.Payment;
import org.example.jvcarsharingservice.model.classes.Rental;
import org.example.jvcarsharingservice.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserIdSpecificationProvider implements SpecificationProvider<Rental> {

    @Override
    public String getKey() {
        return "usersId";
    }

    @Override
    public Specification<Rental> getSpecification(String[] params) {
        return null;
    }

    public Specification<Rental> getSpecification(Long[] params) {
        return (root, query, criteriaBuilder) -> {
            Join<Payment, Rental> rentalJoin = root.join("rental", JoinType.INNER);
            return rentalJoin.get("userId").in(params);
        };
    }

    @Override
    public Specification<Rental> getSpecification(Boolean params) {
        return null;
    }
}

