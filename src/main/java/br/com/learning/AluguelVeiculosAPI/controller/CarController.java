package br.com.learning.AluguelVeiculosAPI.controller;

import br.com.learning.AluguelVeiculosAPI.dto.CarDto.DetailsCarDto;
import br.com.learning.AluguelVeiculosAPI.dto.CarDto.RegisterCarDto;
import br.com.learning.AluguelVeiculosAPI.model.Car;
import br.com.learning.AluguelVeiculosAPI.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping("register")
    @Transactional
    public ResponseEntity<DetailsCarDto> registarCar(@RequestBody RegisterCarDto registerCarDto, UriComponentsBuilder uriBuilder){
        var car = new Car(registerCarDto);
        carRepository.save(car);
        var uri = uriBuilder.path("car/{id}").buildAndExpand(car.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsCarDto(car));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailsCarDto> detailsCar(@PathVariable("id") int id) {
        try {
            var car = carRepository.getReferenceById(id);
            return ResponseEntity.ok().body(new DetailsCarDto(car));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("ListCar")
    public ResponseEntity<List<DetailsCarDto>> listDetaailsCar(Pageable pageable) {
        var list = carRepository.findAll(pageable).stream().map(DetailsCarDto::new).toList();
        if(list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }
}
