package br.com.learning.AluguelVeiculosAPI.dto.HireDto;

import br.com.learning.AluguelVeiculosAPI.dto.CostumerDto.DetailsCostumerDto;
import br.com.learning.AluguelVeiculosAPI.model.Hire;

public record DetailsHireDto(short days, DetailsCostumerDto costumerDto) {
    public DetailsHireDto(Hire hire) {
        this(hire.getDays(), new DetailsCostumerDto(hire.getCostumer()));
    }
}
