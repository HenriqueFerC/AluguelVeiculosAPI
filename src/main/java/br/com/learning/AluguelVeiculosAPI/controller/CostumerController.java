package br.com.learning.AluguelVeiculosAPI.controller;

import br.com.learning.AluguelVeiculosAPI.dto.CostumerDto.DetailsCostumerDto;
import br.com.learning.AluguelVeiculosAPI.dto.CostumerDto.RegisterCostumerDto;
import br.com.learning.AluguelVeiculosAPI.model.Costumer;
import br.com.learning.AluguelVeiculosAPI.repository.CostumerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("costumer")
public class CostumerController {

    @Autowired
    private CostumerRepository costumerRepository;

    @PostMapping("register")
    @Transactional
    public ResponseEntity<DetailsCostumerDto> registerCostumer(@RequestBody RegisterCostumerDto costumerDto, UriComponentsBuilder uriBuilder) {
        var costumer = new Costumer(costumerDto);
        costumerRepository.save(costumer);
        var uri = uriBuilder.path("costumer/{id}").buildAndExpand(costumer.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsCostumerDto(costumer));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailsCostumerDto> detailsCostumer(@PathVariable("id") int id) {
        var costumer = costumerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Costumer not Found!"));
        return ResponseEntity.ok().body(new DetailsCostumerDto(costumer));
    }

    @GetMapping("ListCostumer")
    public ResponseEntity<List<DetailsCostumerDto>> listDetailsCostumer(Pageable pageable) {
        var list = costumerRepository.findAll(pageable).stream().map(DetailsCostumerDto::new).toList();
        if(list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }
}
