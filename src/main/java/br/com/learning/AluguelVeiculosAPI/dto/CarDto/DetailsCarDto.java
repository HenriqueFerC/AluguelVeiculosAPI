package br.com.learning.AluguelVeiculosAPI.dto.CarDto;

import br.com.learning.AluguelVeiculosAPI.model.Car;

import java.math.BigDecimal;

public record DetailsCarDto(String plate, String model, short year, BigDecimal dailyValue, boolean available,
                            short doors) {
    public DetailsCarDto(Car car) {
        this(car.getPlate(), car.getModel(), car.getYear(), car.getDailyValue(), car.isAvailable(), car.getDoors());
    }
}
