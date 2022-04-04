package com.br.completedToDo.service.impl;

import com.br.completedToDo.model.entity.ToDo;
import com.br.completedToDo.model.repository.TodoRepository;
import com.br.completedToDo.payload.ToDoDto;
import com.br.completedToDo.service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ToDoServiceImpl implements ToDoService {

    private final TodoRepository todoRepository;

    public ToDoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public ToDo getToDo(String id) throws Exception {
        return todoRepository.findById(id).orElseThrow(() -> new Exception("Task not found"));
    }

    @Override
    public ToDo saveToDo(ToDoDto toDoDto) {
        ToDo toDo = new ToDo();
        toDo.setTask(toDoDto.getTask());
        toDo.setCreatedAt(new Date());
        return todoRepository.save(toDo);
    }

    @Override
    public ToDo updateToDo(String id, ToDoDto toDoDto) throws Exception {
        ToDo toDo = this.getToDo(id);
        toDo.setTask(toDoDto.getTask());
        return todoRepository.save(toDo);
    }

    @Override
    public void deleteToDo(String id) throws Exception {
        ToDo toDo = this.getToDo(id);
        todoRepository.deleteById(toDo.getId());
    }
}
