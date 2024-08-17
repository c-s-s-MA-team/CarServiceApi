package org.example.jvcarsharingservice.repository.rental;

import org.example.jvcarsharingservice.model.classes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long>,
        JpaSpecificationExecutor<Rental> {

    @Query("SELECT r FROM Rental r "
            + "WHERE r.userId = :userId")
    Optional<Rental> findByUserId(Long userId);
}

