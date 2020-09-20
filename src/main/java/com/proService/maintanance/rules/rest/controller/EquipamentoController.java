package com.proService.maintanance.rules.rest.controller;

import com.proService.maintanance.rules.domain.entity.Equipamento;
import com.proService.maintanance.rules.service.EquipamentoService;
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
@RequestMapping(value = "/equipamento")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Criação de equipamento")
    public ResponseEntity<Equipamento> save(@RequestBody Equipamento equipamento){
        return ResponseEntity.ok(equipamentoService.save(equipamento));
    }

    @PutMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Atualização de equipamento")
    public ResponseEntity<Equipamento> update(@RequestBody Equipamento equipamento, @PathVariable("id") UUID id){
        if(!id.equals(equipamento.getId())){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(equipamentoService.update(equipamento));
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos os equipamentos")
    public ResponseEntity<List<Equipamento>> findAll(){
        return ResponseEntity.ok(equipamentoService.findAll());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca equipamento by id")
    public ResponseEntity<Equipamento> findById(@PathVariable("id")UUID id){

        Optional<Equipamento> equipamento = equipamentoService.findById(id);

        return equipamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id")UUID id){
        equipamentoService.deleteById(id);
    }
}
