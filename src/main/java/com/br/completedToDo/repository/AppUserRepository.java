package com.br.completedToDo.repository;

import com.br.completedToDo.domain.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AppUserRepository extends MongoRepository<AppUser, String> {

    Optional<AppUser> findByUsername(String username);

}
