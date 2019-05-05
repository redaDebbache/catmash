package com.debbache.catmash.repository;

import com.debbache.catmash.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, String> {
}
