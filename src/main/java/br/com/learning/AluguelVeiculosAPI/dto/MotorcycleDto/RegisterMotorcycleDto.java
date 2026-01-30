package br.com.learning.AluguelVeiculosAPI.dto.MotorcycleDto;

import java.math.BigDecimal;

public record RegisterMotorcycleDto(
        String plate,
        String model,
        short year,
        BigDecimal dailyValue,
        boolean available,
        short cylinderCapacity
) {
}
