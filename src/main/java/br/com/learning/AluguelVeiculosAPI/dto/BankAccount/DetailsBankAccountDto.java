package br.com.learning.AluguelVeiculosAPI.dto.BankAccount;

import br.com.learning.AluguelVeiculosAPI.dto.CostumerDto.DetailsCostumerDto;
import br.com.learning.AluguelVeiculosAPI.model.BankAccount;

import java.math.BigDecimal;

public record DetailsBankAccountDto(BigDecimal creditLimit, DetailsCostumerDto costumer) {
    public DetailsBankAccountDto(BankAccount bankAccount) {
        this(bankAccount.getCreditLimit(), new DetailsCostumerDto(bankAccount.getCostumer()));
    }
}
