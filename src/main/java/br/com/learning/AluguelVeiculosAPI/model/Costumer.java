package br.com.learning.AluguelVeiculosAPI.model;

import br.com.learning.AluguelVeiculosAPI.dto.CostumerDto.RegisterCostumerDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Costumer")
public class Costumer {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
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

    @Column(name = "Cpf_Cnpj", length = 14, nullable = false, unique = true)
    private String cpfOrCnpj;

    @OneToOne(mappedBy = "costumer")
    private BankAccount bankAccount;

    @OneToMany(mappedBy = "costumer")
    private List<Hire> hires;

    public void addHires(Hire hire) {
        hires.add(hire);
    }

    public Costumer(RegisterCostumerDto costumerDto) {
        name = costumerDto.name();
        email = costumerDto.email();
        contact = costumerDto.contact();
        costumerType = costumerDto.costumerType();
        cpfOrCnpj = costumerDto.cpfOrCnpj();
    }
}
