package com.br.completedToDo.controller;

import com.br.completedToDo.payload.ToDoDto;
import com.br.completedToDo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/alltask")
    public ResponseEntity getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/task/{id}")
    public ResponseEntity getTask(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PostMapping("/task")
    public ResponseEntity saveTask(@RequestBody @Valid ToDoDto toDoDto, Principal principal) {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/task")
                .toString());
        return ResponseEntity.created(uri).body(taskService.saveTask(toDoDto, principal));
    }

    @PostMapping("/task/{id}")
    public ResponseEntity completeTask(@PathVariable String id, Principal principal) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/task/" + id)
                .toString());
        return ResponseEntity.created(uri).body(taskService.completeTask(id, principal));
    }

    @PutMapping("/task/{id}")
    public ResponseEntity updateTask(@RequestBody @Valid ToDoDto toDoDto, @PathVariable String id) throws Exception {
        return ResponseEntity.accepted().body(taskService.updateTask(id, toDoDto));
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity deleteTask(@PathVariable String id) throws Exception {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
