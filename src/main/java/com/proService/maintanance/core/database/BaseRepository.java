package com.proService.maintanance.core.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T extends BaseClass> extends JpaRepository<T, UUID> {

}
