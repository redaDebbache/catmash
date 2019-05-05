package com.debbache.catmash.repository;

import com.debbache.catmash.dto.BattleDTO;
import com.debbache.catmash.model.Battle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BattleRepository extends JpaRepository<Battle, Long> {
    @Query("select new com.debbache.catmash.dto.BattleDTO(b.id, b.first.id, b.first.imageUrl, b.second.id, b.second.imageUrl) from Battle b where b.id not in (select v.battle.id from Vote v where v.owner.id = ?1) order by b.functionalId asc")
    Page<BattleDTO> findNext(String userId, Pageable pageable);
}
