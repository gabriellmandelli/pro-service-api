package com.proService.maintanance.rules.rest.controller;

import com.proService.maintanance.rules.domain.entity.Manutencao;
import com.proService.maintanance.rules.domain.enums.SituacaoManutencao;
import com.proService.maintanance.rules.service.ManutencaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioManutencaoController {

    @Autowired
    private ManutencaoService manutencaoService;

    @GetMapping(value = "{id}/manutencao", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todas as manutenções de determinado Funcionario pelo ID")
    public ResponseEntity<List<Manutencao>> findOrdemServicoByFuncionarioId(@PathVariable("id") UUID idFuncionario){
        return ResponseEntity.ok(manutencaoService.findManutencaoByFuncionarioId(idFuncionario));
    }

    @GetMapping(value = "{id}/manutencao/{situacaoManutencao}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todas as manutenções de determinado Funcionario pelo ID e Situacao da Manutenção")
    public ResponseEntity<List<Manutencao>> findOrdemServicoByFuncionarioId(@PathVariable("id") UUID idFuncionario,
                                                                            @PathVariable("situacaoManutencao")SituacaoManutencao situacaoManutencao){
        return ResponseEntity.ok(manutencaoService.findManutencaoByFuncionarioId(idFuncionario, situacaoManutencao));
    }

}
