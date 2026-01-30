package br.com.learning.AluguelVeiculosAPI.dto.BankAccount;

import java.math.BigDecimal;

public record RegisterBankAccountDto(
        short agency,
        Integer accountNumber,
        BigDecimal balance,
        BigDecimal creditLimit
) {
}
