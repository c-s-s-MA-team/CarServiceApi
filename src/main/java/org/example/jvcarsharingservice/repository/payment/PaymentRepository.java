package org.example.jvcarsharingservice.repository.payment;

import java.util.Optional;
import org.example.jvcarsharingservice.model.classes.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>,
        JpaSpecificationExecutor<Payment> {

    Optional<Payment> findBySessionId(String sessionId);
}
