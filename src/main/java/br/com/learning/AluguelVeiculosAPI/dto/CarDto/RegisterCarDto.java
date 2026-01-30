package br.com.learning.AluguelVeiculosAPI.dto.CarDto;

import java.math.BigDecimal;

public record RegisterCarDto(
        String plate,
        String model,
        short year,
        BigDecimal dailyValue,
        boolean available,
        short doors
) {
}
