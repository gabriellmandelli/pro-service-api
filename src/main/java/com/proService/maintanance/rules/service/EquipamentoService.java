package com.proService.maintanance.rules.service;

import com.proService.maintanance.rules.domain.entity.Equipamento;
import com.proService.maintanance.rules.domain.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    public Equipamento save(Equipamento equipamento){
        return equipamentoRepository.save(equipamento);
    }

    public Equipamento update(Equipamento equipamento){
        return equipamentoRepository.save(equipamento);
    }

    public Optional<Equipamento> findById(UUID id){
        return equipamentoRepository.findById(id);
    }

    public List<Equipamento> findAll(){
        return equipamentoRepository.findAll();
    }

    public void deleteById(UUID id){
        if (equipamentoRepository.findById(id).isPresent()){
            equipamentoRepository.deleteById(id);
        }
    }
}
