package br.com.learning.AluguelVeiculosAPI.model;

import br.com.learning.AluguelVeiculosAPI.dto.CarDto.RegisterCarDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Car")
public class Car extends Veicle {

    @Column(name = "Doors", nullable = false, length = 4)
    private short doors;

    @OneToMany(mappedBy = "car")
    private List<Hire> hires;

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

    public void addHire(Hire hire) {
        hires.add(hire);
    }
}
