package br.com.learning.AluguelVeiculosAPI.controller;

import br.com.learning.AluguelVeiculosAPI.dto.MotorcycleDto.DetailsMotorcycleDto;
import br.com.learning.AluguelVeiculosAPI.dto.MotorcycleDto.RegisterMotorcycleDto;
import br.com.learning.AluguelVeiculosAPI.model.Motorcycle;
import br.com.learning.AluguelVeiculosAPI.repository.MotorcycleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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
        var uri = uriBuilder.path("motorcycle/{id}").buildAndExpand(moto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsMotorcycleDto(moto));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailsMotorcycleDto> detailsMotorcycle(@PathVariable("id") int id) {
        try {
            var moto = motorcycleRepository.getReferenceById(id);
            return ResponseEntity.ok().body(new DetailsMotorcycleDto(moto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("ListMoto")
    public ResponseEntity<List<DetailsMotorcycleDto>> listDetailsMotorcycle(Pageable pageable){
        var lista = motorcycleRepository.findAll(pageable).stream().map(DetailsMotorcycleDto::new).toList();
        if(lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }
}
