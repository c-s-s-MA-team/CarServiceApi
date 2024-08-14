package org.example.jvcarsharingservice.dto.car;

import java.math.BigDecimal;

public record CarRequestDto(
        String model,
        String brand,
        String type,
        int inventory,
        BigDecimal dailyFee
) {
}
