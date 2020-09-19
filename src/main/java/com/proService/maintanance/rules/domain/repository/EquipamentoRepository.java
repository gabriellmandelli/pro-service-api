package com.proService.maintanance.rules.domain.repository;

import com.proService.maintanance.core.database.BaseRepository;
import com.proService.maintanance.rules.domain.entity.Equipamento;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EquipamentoRepository extends BaseRepository<Equipamento> {

    Optional<Equipamento> findByClienteIdAndId(UUID clienteId, UUID equipamentoId);

    Optional<Equipamento> findByClienteId(UUID clienteId);
}
