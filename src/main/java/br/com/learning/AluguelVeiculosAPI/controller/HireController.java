package br.com.learning.AluguelVeiculosAPI.controller;

import br.com.learning.AluguelVeiculosAPI.dto.HireDto.DetailsHireDto;
import br.com.learning.AluguelVeiculosAPI.dto.HireDto.RegisterHireDto;
import br.com.learning.AluguelVeiculosAPI.model.Car;
import br.com.learning.AluguelVeiculosAPI.model.Costumer;
import br.com.learning.AluguelVeiculosAPI.model.Hire;
import br.com.learning.AluguelVeiculosAPI.model.Motorcycle;
import br.com.learning.AluguelVeiculosAPI.repository.CarRepository;
import br.com.learning.AluguelVeiculosAPI.repository.CostumerRepository;
import br.com.learning.AluguelVeiculosAPI.repository.HireRepository;
import br.com.learning.AluguelVeiculosAPI.repository.MotorcycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("hire")
public class HireController {

    @Autowired
    private HireRepository hireRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private CostumerRepository costumerRepository;

    @PostMapping("{idCostumer}/car/{idVeicle}")
    @Transactional
    public ResponseEntity<DetailsHireDto> registerCarHire(@PathVariable("idCostumer") int idCostumer, @PathVariable("idVeicle") int id, @RequestBody RegisterHireDto hireDto, UriComponentsBuilder uriBuilder) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not Found!"));
        Costumer costumer = costumerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Costumer not Found!"));

        var hire = new Hire(hireDto, car, costumer);
        hireRepository.save(hire);

        costumer.addHires(hire);
        car.addHire(hire);

        var uri = uriBuilder.path("hire/{id}").buildAndExpand(hire.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsHireDto(hire));
    }

    @PostMapping("{idCostumer}/moto/{idVeicle}")
    @Transactional
    public ResponseEntity<DetailsHireDto> registerMotoHire(@PathVariable("idCostumer") int idCostumer, @PathVariable("idVeicle") int id, @RequestBody RegisterHireDto hireDto, UriComponentsBuilder uriBuilder) {
        Motorcycle moto = motorcycleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto not Found!"));
        Costumer costumer = costumerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Costumer not Found!"));

        var hire = new Hire(hireDto, moto, costumer);
        hireRepository.save(hire);

        costumer.addHires(hire);
        moto.addHire(hire);

        var uri = uriBuilder.path("hire/{id}").buildAndExpand(hire.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsHireDto(hire));
    }

    @GetMapping("list/idCostumer")
    public ResponseEntity<Page<DetailsHireDto>> totalCostumerHires(@RequestParam("idCostumer") int id, Pageable pageable) {
        var list = hireRepository.findByCostumer_Id(id, pageable).map(DetailsHireDto::new);
        if(list.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }
}
