package com.br.completedToDo.service;

import com.br.completedToDo.domain.Task;
import com.br.completedToDo.payload.ToDoDto;

import java.security.Principal;
import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTask(String id) throws Exception;
    Task saveTask(ToDoDto toDoDto, Principal principal);
    Task updateTask(String id, ToDoDto toDoDto) throws Exception;
    Task completeTask(String id, Principal principal) throws Exception;
    void deleteTask(String id) throws Exception;
}
