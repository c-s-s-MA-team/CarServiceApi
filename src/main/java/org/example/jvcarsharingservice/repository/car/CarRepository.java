package org.example.jvcarsharingservice.repository.car;

import org.example.jvcarsharingservice.model.classes.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>,
        JpaSpecificationExecutor<Car> {
}

