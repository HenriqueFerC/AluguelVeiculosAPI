package br.com.learning.AluguelVeiculosAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AluguelVeiculosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluguelVeiculosApiApplication.class, args);
	}

}
