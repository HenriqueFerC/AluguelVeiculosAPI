package br.com.learning.AluguelVeiculosAPI.model;

import br.com.learning.AluguelVeiculosAPI.exception.HireException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@MappedSuperclass
@Getter
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public abstract class Veicle {

    @Id
    @GeneratedValue
    protected Integer id;

    @Column(name = "Plate", length = 8, nullable = false, unique = true)
    protected String plate;

    @Column(name = "Model", length = 20, nullable = false)
    protected String model;

    @Column(name = "Year", length = 4, nullable = false)
    protected short year;

    @Column(name = "Daily_Value", length = 10, nullable = false)
    protected BigDecimal dailyValue;

    @Column(name = "Available", nullable = false)
    protected boolean available;


    public BigDecimal calculateRentValue(short days) {
        return dailyValue.multiply(BigDecimal.valueOf(days));
    }

    public void hire() throws HireException {
        if (!available) {
            throw new HireException("Unable to hire this veicle 'cause isn't available.");
        }
        available = false;
    }

    public void giveBack() throws HireException {
        if (available) {
            throw new HireException("First need's to hire a veicle.");
        }
        available = false;
    }

    public boolean disponibility() {
        return available;
    }
}
