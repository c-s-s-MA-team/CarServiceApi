package org.example.jvcarsharingservice.model.classes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Table(name = "rental")
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE rental SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rental_date", nullable = false)
    private LocalDate rentalDate;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Column(name = "actual_return_date")
    private LocalDate actualReturnDate;

    @Column(name = "car_id", nullable = false)
    private Long carId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private boolean deleted = false;
}
