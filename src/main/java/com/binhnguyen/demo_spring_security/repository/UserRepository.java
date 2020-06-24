package com.binhnguyen.demo_spring_security.repository;

import com.binhnguyen.demo_spring_security.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AppUser,Integer> {
    AppUser findByUsername(String username);
}
