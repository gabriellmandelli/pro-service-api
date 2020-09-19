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
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/ordemservico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Criação de ordem serviço")
    public ResponseEntity<OrdemServico> save(@RequestBody OrdemServico ordemServico){
        return ResponseEntity.ok(ordemServicoService.save(ordemServico));
    }

    @PutMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Atualização de ordem serviço")
    public ResponseEntity<OrdemServico> save(@RequestBody OrdemServico ordemServico, @PathVariable("id") UUID id){
        if(!id.equals(ordemServico.getId()) || ordemServico.getManutencao().getId() == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(ordemServicoService.save(ordemServico));
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos as ordem serviço")
    public ResponseEntity<List<OrdemServico>> findAll(){
        return ResponseEntity.ok(ordemServicoService.findAll());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca ordem serviço by id")
    public ResponseEntity<OrdemServico> findById(@PathVariable("id")UUID id){

        Optional<OrdemServico> ordemServico = ordemServicoService.findById(id);

        return ordemServico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id")UUID id){
        ordemServicoService.deleteById(id);
    }

}
