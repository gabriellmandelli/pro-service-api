package com.proService.maintanance.rules.domain.repository;

import com.proService.maintanance.core.database.BaseRepository;
import com.proService.maintanance.rules.domain.entity.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente> {
}
