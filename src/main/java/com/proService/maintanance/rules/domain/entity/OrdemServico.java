package com.proService.maintanance.rules.domain.entity;

import com.proService.maintanance.core.database.BaseEntity;
import com.proService.maintanance.rules.domain.enums.SituacaoManutencao;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = "ordem_servico")
public class OrdemServico extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "Descrição do procedimento realizado", example = "Verificado solda BGA.", required = true)
    private String descricao;

    @ApiModelProperty(value = "Data da ocorrência", example = "2020-09-22", required = true)
    private Date dataOcorrencia;

    @ApiModelProperty(value = "Situação do serviço", example = "INICIADO", required = true)
    private SituacaoManutencao situacao;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "manutencao_id")
    @ApiModelProperty(value = "Manutenção vinculada", example = "b3fcd11b-8cad-4b46-b9d7-d7cec3e5d45f", required = true)
    private Manutencao manutencao;

}
