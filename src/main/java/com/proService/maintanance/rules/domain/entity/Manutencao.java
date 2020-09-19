package com.proService.maintanance.rules.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proService.maintanance.core.database.BaseEntity;
import com.proService.maintanance.rules.domain.enums.SituacaoManutencao;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = "manutencao")
public class Manutencao extends BaseEntity implements Serializable {

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "funcionario_id")
    @ApiModelProperty(value = "Funcionário responsável", example = "e7650250-07ce-4095-9773-9001ae1d059f", required = true)
    @JsonIgnoreProperties({"manutencoes"})
    private Funcionario funcionario;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "equipamento_id")
    @ApiModelProperty(value = "Equipamento em manutenção", example = "e4409365-514e-4c79-8ad6-72bc990547a9", required = true)
    @JsonIgnoreProperties({"manutencoes"})
    private Equipamento equipamento;

    @ApiModelProperty(value = "Problema do equipamento", example = "Esta apresentando tela azul, possível artefato, verificar solda BGA.", required = true)
    private String problema;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Data de entrada do equipamento para Manutenção", example = "2020-09-19", required = true)
    private Date dataEntrada;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Data da ultima atualização do serviço", example = "2020-09-19", required = true)
    private Date dataUltimaAtualizacao;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Data de retorno do equipamento para o Cliente", example = "2020-09-28")
    private Date dataRetorno;

    @ApiModelProperty(value = "Situação da manutenção", example = "NAO_INICIADO", required = true)
    private SituacaoManutencao situacao;

    @OneToMany(mappedBy = "manutencao", cascade = CascadeType.ALL)
    @ApiModelProperty(value = "Lista com as ordens de serviço da manutenção")
    @JsonIgnoreProperties({"manutencao"})
    private List<OrdemServico> ordemServico;
}
