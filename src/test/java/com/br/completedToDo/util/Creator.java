package com.br.completedToDo.util;

import com.br.completedToDo.model.entity.ToDo;
import com.br.completedToDo.payload.ToDoDto;
import org.bson.types.ObjectId;

import java.util.Date;

public class Creator {

    //To do
    public static String TODO_ID = new ObjectId().toString();
    public static String TODO_TASK = "Test new Task";
    public static String TODO_UPDATED = "Test updated Task";
    public static Date TODO_CREATEDAT = new Date();

    //URL
    public static String TODO_URL = "http://localhost:8080/api/v1/todo";


    public static ToDo createToDo(){
        ToDo toDo = new ToDo();
        toDo.setId(TODO_ID);
        toDo.setTask(TODO_TASK);
        toDo.setCreatedAt(TODO_CREATEDAT);

        return toDo;
    }

    public static ToDoDto createToDoDto(){
        ToDoDto toDoDto = new ToDoDto();
        toDoDto.setTask(TODO_TASK);

        return toDoDto;
    }
}
