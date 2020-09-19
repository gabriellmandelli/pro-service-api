package com.proService.maintanance.rules.service;

import com.proService.maintanance.rules.domain.entity.Funcionario;
import com.proService.maintanance.rules.domain.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario save(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario update(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> findById(UUID id){
        return funcionarioRepository.findById(id);
    }

    public List<Funcionario> findAll(){
        return funcionarioRepository.findAll();
    }

    public void deleteById(UUID id){
        if (funcionarioRepository.findById(id).isPresent()){
            funcionarioRepository.deleteById(id);
        }
    }
}
