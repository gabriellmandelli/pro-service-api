package com.proService.maintanance.rules.rest.controller;

import com.proService.maintanance.rules.domain.entity.Funcionario;
import com.proService.maintanance.rules.service.FuncionarioService;
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
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Criação de funcionario")
    public ResponseEntity<Funcionario> save(@RequestBody Funcionario funcionario){
        return ResponseEntity.ok(funcionarioService.save(funcionario));
    }

    @PutMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Atualização de funcionario")
    public ResponseEntity<Funcionario> save(@RequestBody Funcionario funcionario, @PathVariable("id") UUID id){

        if (!id.equals(funcionario.getId())){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(funcionarioService.update(funcionario));
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos os funcionarios")
    public ResponseEntity<List<Funcionario>> findAll(){
        return ResponseEntity.ok(funcionarioService.findAll());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca funcionario by id")
    public ResponseEntity<Funcionario> findById(@PathVariable("id")UUID id){

        Optional<Funcionario> funcionario = funcionarioService.findById(id);

        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id")UUID id){
        funcionarioService.deleteById(id);
    }
}
