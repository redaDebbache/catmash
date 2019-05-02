package com.debbache.catmash.repository;

import com.debbache.catmash.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, String> {
    @Query("select cat.id from Cat cat")
    List<String> findAllStoredCatsIds();
}
