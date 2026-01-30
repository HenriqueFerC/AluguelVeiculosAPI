package br.com.learning.AluguelVeiculosAPI.repository;

import br.com.learning.AluguelVeiculosAPI.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
