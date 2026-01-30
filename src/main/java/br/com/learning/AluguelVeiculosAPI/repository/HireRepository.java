package br.com.learning.AluguelVeiculosAPI.repository;

import br.com.learning.AluguelVeiculosAPI.model.Hire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface HireRepository extends JpaRepository<Hire, Integer> {
    Page<Hire> findByCostumerId(int id, Pageable pageable);

    Page<Hire> findByCostumerIdAndHireDateBetween(int id, LocalDateTime initialDate, LocalDateTime finalDate, Pageable pageable);
}
