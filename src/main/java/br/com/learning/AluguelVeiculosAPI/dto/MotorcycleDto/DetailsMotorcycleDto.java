package br.com.learning.AluguelVeiculosAPI.dto.MotorcycleDto;

import br.com.learning.AluguelVeiculosAPI.model.Motorcycle;

import java.math.BigDecimal;

public record DetailsMotorcycleDto(String plate, String model, short year, BigDecimal dailyValue, boolean available,
                                   short cylinderCapacity) {
    public DetailsMotorcycleDto(Motorcycle motorcycle) {
        this(motorcycle.getPlate(), motorcycle.getModel(), motorcycle.getYear(), motorcycle.getDailyValue(),
                motorcycle.isAvailable(), motorcycle.getCylinderCapacity());
    }
}
