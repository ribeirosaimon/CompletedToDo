package com.br.completedToDo.controller;

import com.br.completedToDo.model.entity.ToDo;
import com.br.completedToDo.payload.ToDoDto;
import com.br.completedToDo.service.ToDoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/todo")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/{id}")
    public ToDo getTodo(@PathVariable String id) throws Exception {
        return toDoService.getToDo(id);
    }

    @PostMapping
    public ToDo saveToDo(@RequestBody @Valid ToDoDto toDoDto) {
        return toDoService.saveToDo(toDoDto);
    }
}
