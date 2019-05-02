package com.debbache.catmash.repository;

import com.debbache.catmash.model.Battle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattleRepository extends JpaRepository<Battle, Long> {}
