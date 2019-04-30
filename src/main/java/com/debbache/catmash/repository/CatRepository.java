package com.debbache.catmash.repository;

import com.debbache.catmash.model.Cat;
import org.springframework.data.repository.CrudRepository;

public interface CatRepository extends CrudRepository<Cat, String> {
}
