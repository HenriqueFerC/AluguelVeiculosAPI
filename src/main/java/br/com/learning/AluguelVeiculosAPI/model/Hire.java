package br.com.learning.AluguelVeiculosAPI.model;

import br.com.learning.AluguelVeiculosAPI.dto.HireDto.RegisterHireDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "Hire")
public class Hire {

    @Id
    @GeneratedValue
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

    @OneToOne(mappedBy = "hire")
    private Car car;

    @OneToOne(mappedBy = "hire")
    private Motorcycle motorcycle;

    public Hire(RegisterHireDto hireDto) {
        days = hireDto.days();
    }
}
