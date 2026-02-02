package br.com.learning.AluguelVeiculosAPI.model;

import br.com.learning.AluguelVeiculosAPI.dto.MotorcycleDto.RegisterMotorcycleDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Motorcycle")
public class Motorcycle extends Veicle {

    @Column(name = "Cylinder_Capacity", nullable = false, length = 4)
    private short cylinderCapacity;

    @OneToMany(mappedBy = "motorcycle")
    private List<Hire> hires;

    @Override
    public BigDecimal calculateRentValue(short days) {
        BigDecimal totalValue;
        totalValue = dailyValue.multiply(BigDecimal.valueOf(days));
        totalValue = totalValue.multiply(BigDecimal.valueOf(0.9));
        return totalValue;
    }

    public void addHire(Hire hire) {
        hires.add(hire);
    }

    public Motorcycle(Integer id, String plate, String model, short year, BigDecimal dailyValue, boolean available) {
        super(id, plate, model, year, dailyValue, available);
    }

    public Motorcycle(RegisterMotorcycleDto motorcycleDto) {
        plate = motorcycleDto.plate();
        model = motorcycleDto.model();
        year = motorcycleDto.year();
        dailyValue = motorcycleDto.dailyValue();
        available = motorcycleDto.available();
        cylinderCapacity = motorcycleDto.cylinderCapacity();
    }
}
