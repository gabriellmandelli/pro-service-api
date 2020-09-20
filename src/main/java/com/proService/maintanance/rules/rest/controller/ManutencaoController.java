package com.proService.maintanance.rules.rest.controller;

import com.proService.maintanance.rules.domain.entity.Manutencao;
import com.proService.maintanance.rules.service.ManutencaoService;
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
@RequestMapping(value = "/manutencao")
public class ManutencaoController {

    @Autowired
    private ManutencaoService manutencaoService;

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Criação de manutenção")
    public ResponseEntity<Manutencao> save(@RequestBody Manutencao manutencao){
        return ResponseEntity.ok(manutencaoService.save(manutencao));
    }

    @PutMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Atualização de manutenção")
    public ResponseEntity<Manutencao> save(@RequestBody Manutencao manutencao, @PathVariable("id") UUID id){
        if(!id.equals(manutencao.getId())){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(manutencaoService.update(manutencao));
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos as manutenções")
    public ResponseEntity<List<Manutencao>> findAll(){
        return ResponseEntity.ok(manutencaoService.findAll());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca manutenção by id")
    public ResponseEntity<Manutencao> findById(@PathVariable("id")UUID id){

        Optional<Manutencao> manutencao = manutencaoService.findById(id);

        return manutencao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id")UUID id){
        manutencaoService.deleteById(id);
    }
}
