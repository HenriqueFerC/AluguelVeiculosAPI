package br.com.learning.AluguelVeiculosAPI.repository;

import br.com.learning.AluguelVeiculosAPI.model.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, Integer> {
}
