package com.proService.maintanance.rules.domain.repository;

import com.proService.maintanance.core.database.BaseRepository;
import com.proService.maintanance.rules.domain.entity.OrdemServico;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrdemServicoRespository extends BaseRepository<OrdemServico> {
    Optional<List<OrdemServico>> findByManutencaoId(UUID id);
}
