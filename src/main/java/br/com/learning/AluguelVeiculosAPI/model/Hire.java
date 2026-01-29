package br.com.learning.AluguelVeiculosAPI.model;

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
@Table(name = "Hire")
public class Hire {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "Days", length = 3, nullable = false)
    private short days;

    @Column(name = "Total_Value")
    private BigDecimal totalValue;

    @ManyToOne
    @JoinColumn(name = "id_Costumer")
    private Costumer costumer;

    @OneToOne(mappedBy = "hire")
    private Veicle veicle;


}
