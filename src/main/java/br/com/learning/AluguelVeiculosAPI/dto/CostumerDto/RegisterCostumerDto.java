package br.com.learning.AluguelVeiculosAPI.dto.CostumerDto;

import br.com.learning.AluguelVeiculosAPI.model.CostumerType;

public record RegisterCostumerDto(
        String name,
        String email,
        Integer contact,
        CostumerType costumerType,
        String cpfOrCnpj
) {
}
