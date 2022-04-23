package com.br.completedToDo.repository;

import com.br.completedToDo.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TaskRepository extends MongoRepository<Task, String> {
}
