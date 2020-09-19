package com.proService.maintanance.rules.service;

import com.proService.maintanance.rules.domain.entity.Manutencao;
import com.proService.maintanance.rules.domain.entity.OrdemServico;
import com.proService.maintanance.rules.domain.enums.SituacaoManutencao;
import com.proService.maintanance.rules.domain.repository.ManutencaoRespository;
import com.proService.maintanance.rules.domain.repository.OrdemServicoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRespository ordemServicoRespository;

    @Autowired
    private ManutencaoRespository manutencaoRespository;

    public OrdemServico save(OrdemServico ordemServico){

        ordemServico = ordemServicoRespository.save(ordemServico);

        Optional<Manutencao> manutencao = manutencaoRespository.findById(ordemServico.getManutencao().getId());
        if (manutencao.isPresent()){
            manutencao.get().setSituacao(ordemServico.getSituacao());
            manutencao.get().setDataUltimaAtualizacao(ordemServico.getDataOcorrencia());
            if (ordemServico.getSituacao() == SituacaoManutencao.ENTREGUE){
                manutencao.get().setDataRetorno(ordemServico.getDataOcorrencia());
            }
            manutencaoRespository.save(manutencao.get());
        }

        return ordemServico;
    }

    public Optional<OrdemServico> findById(UUID id){
        return ordemServicoRespository.findById(id);
    }

    public List<OrdemServico> findAll(){
        return ordemServicoRespository.findAll();
    }

    public void deleteById(UUID id){
        if (ordemServicoRespository.findById(id).isPresent()){
            ordemServicoRespository.deleteById(id);
        }
    }

    public List<OrdemServico> findByManutencaoFuncionarioId(UUID funcionarioId){
        return ordemServicoRespository.findByManutencaoFuncionarioIdOrderByDataOcorrencia(funcionarioId);
    }

    public List<OrdemServico> findByManutencaoId(UUID manutencaoId){
        return ordemServicoRespository.findByManutencaoId(manutencaoId);
    }

}
