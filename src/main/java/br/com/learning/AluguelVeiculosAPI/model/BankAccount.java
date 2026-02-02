package br.com.learning.AluguelVeiculosAPI.model;

import br.com.learning.AluguelVeiculosAPI.dto.BankAccount.RegisterBankAccountDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BankAccount")
public class BankAccount {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
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

    public BankAccount(RegisterBankAccountDto bankAccountDto, Costumer costumer) {
        agency = bankAccountDto.agency();
        accountNumber = bankAccountDto.accountNumber();
        balance = bankAccountDto.balance();
        creditLimit = bankAccountDto.creditLimit();
        this.costumer = costumer;
    }
}
