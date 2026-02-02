package br.com.learning.AluguelVeiculosAPI.controller;

import br.com.learning.AluguelVeiculosAPI.dto.MotorcycleDto.DetailsMotorcycleDto;
import br.com.learning.AluguelVeiculosAPI.dto.MotorcycleDto.RegisterMotorcycleDto;
import br.com.learning.AluguelVeiculosAPI.model.Motorcycle;
import br.com.learning.AluguelVeiculosAPI.repository.MotorcycleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
        var moto = motorcycleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorcycle not Found!"));
        return ResponseEntity.ok().body(new DetailsMotorcycleDto(moto));
    }

    @GetMapping("ListMoto")
    public ResponseEntity<List<DetailsMotorcycleDto>> listDetailsMotorcycle(Pageable pageable){
        var list = motorcycleRepository.findAll(pageable).stream().map(DetailsMotorcycleDto::new).toList();
        if(list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }
}
