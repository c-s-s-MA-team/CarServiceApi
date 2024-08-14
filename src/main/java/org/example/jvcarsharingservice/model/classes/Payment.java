package org.example.jvcarsharingservice.model.classes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.jvcarsharingservice.model.enums.PaymentType;
import org.example.jvcarsharingservice.model.enums.Status;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE payment SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType type;

    @Column(name = "rental_id", nullable = false)
    private Long rentalId;

    @Column(name = "session_url", nullable = false)
    private String sessionUrl;

    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @Column(name = "amount_to_pay", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountToPay;

    @Column(nullable = false)
    private boolean deleted = false;
}

