package com.proService.maintanance.rules.domain.entity;

import com.proService.maintanance.core.database.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = "equipamento")
public class Equipamento extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @ApiModelProperty(value = "Id do Cliente dono do equipamento", example = "b48100c3-d002-4427-88b5-7387979ccb84", required = true)
    private Cliente cliente;

    @ApiModelProperty(value = "Tipo do equipamento", example = "Placa de video", required = true)
    private String tipo;

    @ApiModelProperty(value = "Marca do equipamento", example = "Asus", required = true)
    private String marca;

    @ApiModelProperty(value = "Descrição do equipamento", example = "Numero de série: 44846546542031-dsa", required = true)
    private String descricao;

    @OneToMany(mappedBy = "equipamento")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ApiModelProperty(value = "Manutenções realizadas")
    private List<Manutencao> manutencoes;

}
