package br.com.learning.AluguelVeiculosAPI.controller;

import br.com.learning.AluguelVeiculosAPI.dto.BankAccount.DetailsBankAccountDto;
import br.com.learning.AluguelVeiculosAPI.dto.BankAccount.RegisterBankAccountDto;
import br.com.learning.AluguelVeiculosAPI.model.BankAccount;
import br.com.learning.AluguelVeiculosAPI.model.Costumer;
import br.com.learning.AluguelVeiculosAPI.repository.BankAccountRepository;
import br.com.learning.AluguelVeiculosAPI.repository.CostumerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("bankAccount")
public class BankAccountController {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private CostumerRepository costumerRepository;

    @PostMapping("register/{idCostumer}")
    @Transactional
    public ResponseEntity<DetailsBankAccountDto> registerBankAccount(@PathVariable("idCostumer") int id, @RequestBody RegisterBankAccountDto bankAccountDto, UriComponentsBuilder uriBuilder) {
        Costumer costumer = costumerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Costumer not Found!"));

        var bankAccount = new BankAccount(bankAccountDto, costumer);
        bankAccountRepository.save(bankAccount);
        costumer.setBankAccount(bankAccount);

        var uri = uriBuilder.path("bankAccount/{id}").buildAndExpand(bankAccount.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsBankAccountDto(bankAccount));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailsBankAccountDto> detailsBankAccount(@PathVariable("id") int id) {
        try {
            var bankAccount = bankAccountRepository.getReferenceById(id);
            return ResponseEntity.ok().body(new DetailsBankAccountDto(bankAccount));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
