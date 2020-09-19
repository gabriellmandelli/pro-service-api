package com.proService.maintanance.rules.domain.entity;

import com.proService.maintanance.core.database.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
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

    @OneToMany(mappedBy = "cliente")
    @ApiModelProperty(value = "Equipamentos do cliente")
    private List<Equipamento> equipamentos;
}
