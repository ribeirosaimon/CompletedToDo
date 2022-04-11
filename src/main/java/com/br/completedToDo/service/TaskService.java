package com.br.completedToDo.service;

import com.br.completedToDo.model.entity.Task;
import com.br.completedToDo.payload.ToDoDto;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTask(String id) throws Exception;
    Task saveTask(ToDoDto toDoDto);
    Task updateTask(String id, ToDoDto toDoDto) throws Exception;
    Task completeTask(String id) throws Exception;
    void deleteTask(String id) throws Exception;
}
