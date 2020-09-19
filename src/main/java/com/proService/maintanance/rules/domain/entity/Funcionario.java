package com.proService.maintanance.rules.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proService.maintanance.core.database.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name = "funcionario")
public class Funcionario extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "Nome do funcionario", example = "João Zinho", required = true)
    private String nome;

    @ApiModelProperty(value = "Email do funcionario", example = "joao-zinho@gmail.com", required = true)
    private String email;

    @ApiModelProperty(value = "Cargo do funcionario", example = "TECNICO", required = true)
    private String cargo;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    @ApiModelProperty(value = "Manutenções realizadas pelo funcionario")
    @JsonIgnoreProperties({"funcionario", "equipamento"})
    private List<Manutencao> manutencoes;
}
