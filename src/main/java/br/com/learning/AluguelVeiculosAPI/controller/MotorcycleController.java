package br.com.learning.AluguelVeiculosAPI.controller;

import br.com.learning.AluguelVeiculosAPI.dto.MotorcycleDto.DetailsMotorcycleDto;
import br.com.learning.AluguelVeiculosAPI.dto.MotorcycleDto.RegisterMotorcycleDto;
import br.com.learning.AluguelVeiculosAPI.model.Motorcycle;
import br.com.learning.AluguelVeiculosAPI.repository.MotorcycleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("motorcycle")
public class MotorcycleController {

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @PostMapping("register")
    @Transactional
    public ResponseEntity<DetailsMotorcycleDto> registerMotorcycle(@RequestBody RegisterMotorcycleDto motorcycleDto, UriComponentsBuilder uriBuilder){
        var moto = new Motorcycle(motorcycleDto);
        motorcycleRepository.save(moto);
        var uri = uriBuilder.path("motorcycle/{id}").buildAndExpand(uriBuilder).toUri();
        return ResponseEntity.created(uri).body(new DetailsMotorcycleDto(moto));
    }


}
