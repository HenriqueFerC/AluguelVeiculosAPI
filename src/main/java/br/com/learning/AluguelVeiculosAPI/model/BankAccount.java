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
@Table(name = "BankAccount")
public class BankAccount {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "Agency", length = 4, nullable = false)
    private short agency;

    @Column(name = "Account_Number", length = 6, nullable = false)
    private Integer accountNumber;

    @Column(name = "Balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "Credit_Limit", nullable = false)
    private BigDecimal creditLimit;

    @OneToOne
    @JoinColumn(name = "id_Costumer")
    private Costumer costumer;
}
