package org.example.jvcarsharingservice.model.classes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.jvcarsharingservice.model.enums.Type;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@Entity
@Table(name = "car")
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE car SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private int inventory;

    @Column(name = "daily_fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal dailyFee;

    @Column(nullable = false)
    private boolean deleted = false;
}
