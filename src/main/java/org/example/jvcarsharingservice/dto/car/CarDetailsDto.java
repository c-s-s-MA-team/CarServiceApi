package org.example.jvcarsharingservice.dto.car;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarDetailsDto {
    private Long id;
    private String model;
    private String brand;
    private String type;
    private int inventory;
    private BigDecimal dailyFee;
}
