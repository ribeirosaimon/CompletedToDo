package com.br.completedToDo.util;

import com.br.completedToDo.domain.Task;
import com.br.completedToDo.payload.ToDoDto;
import org.bson.types.ObjectId;

import java.util.Date;

public class Creator {

    public static String USER_ROLE = "user";
    public static String ADMIN_ROLE = "admin";

    //To do
    public static String TODO_ID = new ObjectId().toString();
    public static String TODO_TASK = "Test new Task";
    public static String TODO_UPDATED = "Test updated Task";
    public static Date TODO_CREATEDAT = new Date();

    //URL
    public static String TODO_URL = "http://localhost:8080/api/v1/todo";

    public static Task createToDo(){
        Task task = new Task();
        task.setId(TODO_ID);
        task.setTask(TODO_TASK);
        task.setCreatedAt(TODO_CREATEDAT);

        return task;
    }

    public static ToDoDto createToDoDto(){
        ToDoDto toDoDto = new ToDoDto();
        toDoDto.setTask(TODO_TASK);

        return toDoDto;
    }
}