package com.proService.maintanance.rules.service;

import com.proService.maintanance.rules.domain.entity.Cliente;
import com.proService.maintanance.rules.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> findById(UUID clienteId){
        return clienteRepository.findById(clienteId);
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public void deleteById(UUID id){
        if (clienteRepository.findById(id).isPresent()){
            clienteRepository.deleteById(id);
        }
    }
}
