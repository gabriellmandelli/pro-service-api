package com.proService.maintanance.rules.domain.repository;

import com.proService.maintanance.core.database.BaseRepository;
import com.proService.maintanance.rules.domain.entity.OrdemServico;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrdemServicoRespository extends BaseRepository<OrdemServico> {
    List<OrdemServico> findByManutencaoId(UUID id);
    List<OrdemServico> findByManutencaoFuncionarioIdOrderByDataOcorrencia(UUID id);
}
