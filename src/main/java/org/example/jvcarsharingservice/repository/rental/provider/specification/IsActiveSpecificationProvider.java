package org.example.jvcarsharingservice.repository.rental.provider.specification;

import org.example.jvcarsharingservice.model.classes.Rental;
import org.example.jvcarsharingservice.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class IsActiveSpecificationProvider implements SpecificationProvider<Rental> {
    @Override
    public String getKey() {
        return "isActive";
    }

    @Override
    public Specification<Rental> getSpecification(String[] params) {
        return null;
    }

    @Override
    public Specification<Rental> getSpecification(Long[] params) {
        return null;
    }

    @Override
    public Specification<Rental> getSpecification(Boolean params) {
        return (root, query, criteriaBuilder) -> {
            if(params == null) {
                return null;
            }
            if (params) {
                return criteriaBuilder.and(
                        criteriaBuilder.isNotNull(
                                root.get("actualReturnDate")
                        ),
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("returnDate"),
                                LocalDate.now()
                        ));
            } else {
                return criteriaBuilder.or(
                        criteriaBuilder.isNotNull(
                                root.get("actualReturnDate")
                        ),
                        criteriaBuilder.lessThan(root.get("returnDate"),
                                LocalDate.now()
                        ));
            }
        };
    }
}
