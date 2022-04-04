package com.br.completedToDo.service;

import com.br.completedToDo.model.entity.ToDo;
import com.br.completedToDo.payload.ToDoDto;

public interface ToDoService {

    ToDo getToDo(String id) throws Exception;
    ToDo saveToDo(ToDoDto toDoDto);
}
