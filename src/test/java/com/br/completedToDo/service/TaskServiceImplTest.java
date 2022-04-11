package com.br.completedToDo.service;

import com.br.completedToDo.model.entity.Task;
import com.br.completedToDo.payload.ToDoDto;
import com.br.completedToDo.util.Creator;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DisplayName("ToDo Service Test")
class TaskServiceImplTest {
    @Mock
    private TaskService service;

    @BeforeEach
    void setTup() throws Exception {
        Task task = Creator.createToDo();

        Task updatedTask = Creator.createToDo();
        updatedTask.setTask(Creator.TODO_UPDATED);
        updatedTask.setUpdatedAt(Creator.TODO_CREATEDAT);

        BDDMockito.given(service.getTask(ArgumentMatchers.anyString())).willReturn(task);
        BDDMockito.given(service.saveTask(ArgumentMatchers.any())).willReturn(task);
        BDDMockito.given(service.updateTask(ArgumentMatchers.any(), ArgumentMatchers.any())).willReturn(updatedTask);
        BDDMockito.doNothing().when(service).deleteTask(ArgumentMatchers.anyString());
    }
    @Test
    void getToDo() throws Exception {
        Task task = service.getTask(Creator.TODO_ID);
        Assertions.assertEquals(task.getId(), Creator.TODO_ID);
    }

    @Test
    void saveToDo() {
        ToDoDto toDoDto = Creator.createToDoDto();
        Task task = service.saveTask(toDoDto);

        Assertions.assertEquals(task.getId(), Creator.TODO_ID);
        Assertions.assertEquals(task.getTask(), Creator.TODO_TASK);
        Assertions.assertNotNull(task.getCreatedAt());
    }

    @Test
    void updateToDo() throws Exception {
        Task updatedTask = Creator.createToDo();
        updatedTask.setTask(Creator.TODO_UPDATED);
        updatedTask.setUpdatedAt(Creator.TODO_CREATEDAT);

        Task task = service.updateTask(updatedTask.getId(), new ToDoDto());

        Assertions.assertEquals(task.getId(), Creator.TODO_ID);
        Assertions.assertEquals(task.getTask(), Creator.TODO_UPDATED);
        Assertions.assertNotNull(task.getUpdatedAt());
    }

    @Test
    void deleteToDo() throws Exception {
        service.deleteTask(Creator.TODO_ID);

        org.assertj.core.api.Assertions
                .assertThatCode(() -> service.deleteTask(new ObjectId().toString()))
                .doesNotThrowAnyException();
    }
}