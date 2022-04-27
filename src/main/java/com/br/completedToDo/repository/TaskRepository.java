package com.br.completedToDo.repository;

import com.br.completedToDo.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findAllByIdUser(String id);
}
