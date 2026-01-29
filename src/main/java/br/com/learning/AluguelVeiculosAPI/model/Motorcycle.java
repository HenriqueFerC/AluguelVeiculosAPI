package br.com.learning.AluguelVeiculosAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter(AccessLevel.NONE)
@Entity
@Table(name = "Motorcycle")
public class Motorcycle extends Veicle{

    public Motorcycle(Integer id, String plate, String model, short year, BigDecimal dailyValue, boolean available) {
        super(id, plate, model, year, dailyValue, available);
    }

    @Override
    public BigDecimal calculateRentValue(short days) {
        BigDecimal totalValue;
        totalValue = dailyValue.multiply(BigDecimal.valueOf(days));
        totalValue = totalValue.multiply(BigDecimal.valueOf(0.9));
        return totalValue;
    }
}
