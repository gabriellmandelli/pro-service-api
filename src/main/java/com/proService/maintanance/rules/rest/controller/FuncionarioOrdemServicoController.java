package com.proService.maintanance.rules.rest.controller;

import com.proService.maintanance.rules.domain.entity.OrdemServico;
import com.proService.maintanance.rules.service.OrdemServicoService;
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
public class FuncionarioOrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @GetMapping(value = "{id}/ordemservico", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todas as ordens de serviço de determinado Funcionario")
    public ResponseEntity<List<OrdemServico>> findOrdemServicoByFuncionarioId(@PathVariable("id") UUID idFuncionario){
        return ResponseEntity.ok(ordemServicoService.findByManutencaoFuncionarioId(idFuncionario));
    }

}
