package br.com.learning.AluguelVeiculosAPI.model;

import br.com.learning.AluguelVeiculosAPI.dto.CostumerDto.RegisterCostumerDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@Entity
@Table(name = "Costumer")
public class Costumer {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "Name", length = 60, nullable = false)
    private String name;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "Contact", length = 11, nullable = false)
    private Integer contact;

    @Column(name = "Costumer_Type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CostumerType costumerType;

    @Column(name = "Cpf_Cnpj", length = 14, nullable = false)
    private String cpfOrCnpj;

    @OneToOne(mappedBy = "costumer")
    private BankAccount bankAccount;

    @OneToMany(mappedBy = "costumer")
    private List<Hire> hires;

    public Costumer(RegisterCostumerDto costumerDto) {
        name = costumerDto.name();
        email = costumerDto.email();
        contact = costumerDto.contact();
        costumerType = costumerDto.costumerType();
        cpfOrCnpj = costumerDto.cpfOrCnpj();
    }
}
