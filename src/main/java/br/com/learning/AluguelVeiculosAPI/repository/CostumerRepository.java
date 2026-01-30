package br.com.learning.AluguelVeiculosAPI.repository;

import br.com.learning.AluguelVeiculosAPI.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRepository extends JpaRepository<Costumer, Integer> {
}
