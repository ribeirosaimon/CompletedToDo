package com.br.completedToDo.service;

import com.br.completedToDo.model.entity.ToDo;
import com.br.completedToDo.payload.ToDoDto;
import org.springframework.http.ResponseEntity;

public interface ToDoService {

    ToDo getToDo(String id) throws Exception;
    ToDo saveToDo(ToDoDto toDoDto);
    ToDo updateToDo(String id, ToDoDto toDoDto) throws Exception;
    void deleteToDo(String id) throws Exception;
}
