package br.com.learning.AluguelVeiculosAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@Entity
@Table(name = "Car")
public class Car extends Veicle{

    public Car(Integer id, String plate, String model, short year, BigDecimal dailyValue, boolean available, Hire hire) {
        super(id, plate, model, year, dailyValue, available, hire);
    }

    @Column(name = "Doors", nullable = false, length = 4)
    private short doors;
}
