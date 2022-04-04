package com.br.completedToDo.controller;

import com.br.completedToDo.model.entity.ToDo;
import com.br.completedToDo.payload.ToDoDto;
import com.br.completedToDo.service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/todo")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getToDo(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(toDoService.getToDo(id));
    }

    @PostMapping
    public ResponseEntity saveToDo(@RequestBody @Valid ToDoDto toDoDto) {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/todo")
                .toString());
        return ResponseEntity.created(uri).body(toDoService.saveToDo(toDoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateToDo(@RequestBody @Valid ToDoDto toDoDto, @PathVariable String id) throws Exception {
        return ResponseEntity.accepted().body(toDoService.updateToDo(id, toDoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteToDo(@PathVariable String id) throws Exception {
        toDoService.deleteToDo(id);
        return ResponseEntity.noContent().build();
    }
}
