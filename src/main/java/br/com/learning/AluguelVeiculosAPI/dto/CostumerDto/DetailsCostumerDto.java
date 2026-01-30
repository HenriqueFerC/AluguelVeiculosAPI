package br.com.learning.AluguelVeiculosAPI.dto.CostumerDto;

import br.com.learning.AluguelVeiculosAPI.model.Costumer;
import br.com.learning.AluguelVeiculosAPI.model.CostumerType;

public record DetailsCostumerDto(String name, String email, Integer contact, CostumerType costumerType,
                                 String cpfOrCnpj) {
    public DetailsCostumerDto(Costumer costumer) {
        this(costumer.getName(), costumer.getEmail(), costumer.getContact(), costumer.getCostumerType(), costumer.getCpfOrCnpj());
    }
}
