package com.br.completedToDo.service.Impl;

import com.br.completedToDo.domain.Task;
import com.br.completedToDo.repository.TaskRepository;
import com.br.completedToDo.payload.ToDoDto;
import com.br.completedToDo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTask(String id) throws Exception {
        return taskRepository.findById(id).orElseThrow(() -> new Exception("Task not found"));
    }

    @Override
    public Task saveTask(ToDoDto toDoDto) {
        Task task = new Task();
        task.setCompletedTask(false);
        task.setTask(toDoDto.getTask());
        task.setCreatedAt(new Date());

        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(String id, ToDoDto toDoDto) throws Exception {
        Task task = this.getTask(id);
        task.setTask(toDoDto.getTask());
        task.setUpdatedAt(new Date());

        return taskRepository.save(task);
    }

    @Override
    public Task completeTask(String id) throws Exception {
        Task task = this.getTask(id);
        task.setCompletedTask(true);
        task.setUpdatedAt(new Date());

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(String id) throws Exception {
        Task task = this.getTask(id);
        taskRepository.deleteById(task.getId());
    }
}
