package br.com.learning.AluguelVeiculosAPI.model;

import br.com.learning.AluguelVeiculosAPI.dto.HireDto.RegisterHireDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "Hire")
public class Hire {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "Days", length = 3, nullable = false)
    private short days;

    @Column(name = "Total_Value")
    private BigDecimal totalValue;

    @Column(name = "Hire_Date", nullable = false)
    @CreatedDate
    private LocalDateTime hireDate;

    @Column(name = "Give_Back_Date")
    private LocalDateTime giveBackDate;

    @ManyToOne
    @JoinColumn(name = "id_Costumer")
    private Costumer costumer;

    @ManyToOne
    @JoinColumn(name = "Id_Car")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "Id_Moto")
    private Motorcycle motorcycle;

    public Hire(RegisterHireDto hireDto) {
        days = hireDto.days();
    }
}
