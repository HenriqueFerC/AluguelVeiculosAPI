package br.com.learning.AluguelVeiculosAPI.repository;

import br.com.learning.AluguelVeiculosAPI.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
}
