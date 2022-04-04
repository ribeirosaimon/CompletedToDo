package com.br.completedToDo.model.repository;

import com.br.completedToDo.model.entity.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TodoRepository extends MongoRepository<ToDo, String> {
}
