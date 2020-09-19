package com.proService.maintanance.rules.domain.repository;

import com.proService.maintanance.core.database.BaseRepository;
import com.proService.maintanance.rules.domain.entity.Manutencao;
import com.proService.maintanance.rules.domain.enums.SituacaoManutencao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ManutencaoRespository extends BaseRepository<Manutencao> {
    Optional<List<Manutencao>> findByFuncionarioId(UUID funcionarioId);
    Optional<List<Manutencao>> findByFuncionarioIdAndSituacao(UUID funcionarioId, SituacaoManutencao situacaoManutencao);
}
