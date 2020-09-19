package com.proService.maintanance.rules.service;

import com.proService.maintanance.rules.domain.entity.Manutencao;
import com.proService.maintanance.rules.domain.entity.OrdemServico;
import com.proService.maintanance.rules.domain.enums.SituacaoManutencao;
import com.proService.maintanance.rules.domain.repository.ManutencaoRespository;
import com.proService.maintanance.rules.domain.repository.OrdemServicoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ManutencaoService {

    @Autowired
    private ManutencaoRespository manutencaoRespository;

    @Autowired
    private OrdemServicoRespository ordemServicoRespository;

    public Manutencao save(Manutencao manutencao){
        if (manutencao.getSituacao() == null){
            manutencao.setSituacao(SituacaoManutencao.NAO_INICIADO);
        }
        Manutencao manutencaoDb = manutencaoRespository.save(manutencao);
        OrdemServico ordemServico = ordemServicoRespository.save(new OrdemServico(manutencaoDb.getProblema(), manutencaoDb.getDataEntrada(), manutencao.getSituacao(), manutencaoDb));
        manutencaoDb.setOrdemServico(Collections.singletonList(ordemServico));

        return manutencaoDb;
    }

    public Manutencao update(Manutencao manutencao){
        return manutencaoRespository.save(manutencao);
    }

    public Optional<Manutencao> findById(UUID id){
        return manutencaoRespository.findById(id);
    }

    public List<Manutencao> findAll(){
        return manutencaoRespository.findAll();
    }

    public List<Manutencao> findManutencaoByFuncionarioId(UUID funcionarioID){
        return manutencaoRespository.findByFuncionarioId(funcionarioID);
    }

    public List<Manutencao> findManutencaoByFuncionarioId(UUID funcionarioID, SituacaoManutencao situacaoManutencao){
        return manutencaoRespository.findByFuncionarioIdAndSituacao(funcionarioID, situacaoManutencao);
    }

    public void deleteById(UUID id){
        if (manutencaoRespository.findById(id).isPresent()){
            manutencaoRespository.deleteById(id);
        }
    }
}
