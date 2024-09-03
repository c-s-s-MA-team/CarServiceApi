package org.example.jvcarsharingservice.repository.payment.provider.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.example.jvcarsharingservice.model.classes.Payment;
import org.example.jvcarsharingservice.model.classes.Rental;
import org.example.jvcarsharingservice.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserIdSpecificationProvider implements SpecificationProvider<Rental> {

    public static final String USERS_ID = "usersId";

    @Override
    public String getKey() {
        return USERS_ID;
    }

    @Override
    public Specification<Rental> getSpecification(String[] params) {
        return null;
    }

    public Specification<Rental> getSpecification(Long[] params) {
        return (root, query, criteriaBuilder) -> {
            Join<Payment, Rental> rentalJoin = root.join("rental", JoinType.INNER);
            return rentalJoin.get(USERS_ID).in(params);
        };
    }

    @Override
    public Specification<Rental> getSpecification(Boolean params) {
        return null;
    }
}

