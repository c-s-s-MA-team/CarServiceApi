package org.example.jvcarsharingservice.repository.payment;

import org.example.jvcarsharingservice.model.classes.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>,
        JpaSpecificationExecutor<Payment> {

    @Query("SELECT p FROM Payment p " +
            "WHERE p.sessionId = :sessionId")
    Optional<Payment> findBySessionId(String sessionId);
}
