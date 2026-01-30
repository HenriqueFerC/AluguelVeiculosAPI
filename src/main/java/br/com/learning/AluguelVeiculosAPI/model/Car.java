package br.com.learning.AluguelVeiculosAPI.model;

import br.com.learning.AluguelVeiculosAPI.dto.CarDto.RegisterCarDto;
import jakarta.persistence.*;
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
public class Car extends Veicle {

    @Column(name = "Doors", nullable = false, length = 4)
    private short doors;

    @OneToOne
    @JoinColumn(name = "Id_Hire")
    private Hire hire;

    public Car(Integer id, String plate, String model, short year, BigDecimal dailyValue, boolean available) {
        super(id, plate, model, year, dailyValue, available);
    }

    public Car(RegisterCarDto carDto) {
        plate = carDto.plate();
        model = carDto.model();
        year = carDto.year();
        dailyValue = carDto.dailyValue();
        available = carDto.available();
        doors = carDto.doors();
    }
}
