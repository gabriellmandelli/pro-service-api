package com.proService.maintanance.rules.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proService.maintanance.core.database.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name = "cliente")
public class Cliente extends BaseEntity implements Serializable {

    @Column(name = "nome")
    @ApiModelProperty(value = "Nome do cliente", example = "Gabriel Mandelli", required = true)
    private String nome;

    @Column(name = "endereco")
    @ApiModelProperty(value = "Endereco do cliente", example = "São Bento Baixo, ", required = true)
    private String endereco;

    @Column(name = "telefone")
    @ApiModelProperty(value = "Telefone do cliente", example = "48999999999", required = true)
    private String telefone;

    @Column(name = "email")
    @ApiModelProperty(value = "Email do cliente", example = "gabriel@gmail.com", required = true)
    private String email;

    @ApiModelProperty(value = "Equipamentos do cliente")
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"cliente"})
    private List<Equipamento> equipamentos;
}
