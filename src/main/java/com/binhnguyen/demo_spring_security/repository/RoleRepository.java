package com.binhnguyen.demo_spring_security.repository;

import com.binhnguyen.demo_spring_security.model.AppRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<AppRole,Integer> {
    AppRole findByName(String name);
}
