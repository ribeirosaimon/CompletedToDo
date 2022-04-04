package com.br.completedToDo.service;

import com.br.completedToDo.model.entity.ToDo;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("ToDo Service Test")
class ToDoServiceImplTest {
    @Mock
    private ToDoService service;

    @BeforeEach
    void setTup() throws Exception {
        ToDo toDo = Creator.createToDo();

        ToDo updatedToDo = Creator.createToDo();
        updatedToDo.setTask(Creator.TODO_UPDATED);
        updatedToDo.setUpdatedAt(Creator.TODO_CREATEDAT);

        BDDMockito.given(service.getToDo(ArgumentMatchers.anyString())).willReturn(toDo);
        BDDMockito.given(service.saveToDo(ArgumentMatchers.any())).willReturn(toDo);
        BDDMockito.given(service.updateToDo(ArgumentMatchers.any(), ArgumentMatchers.any())).willReturn(updatedToDo);
        BDDMockito.doNothing().when(service).deleteToDo(ArgumentMatchers.anyString());
    }
    @Test
    void getToDo() throws Exception {
        ToDo toDo = service.getToDo(Creator.TODO_ID);
        Assertions.assertEquals(toDo.getId(), Creator.TODO_ID);
    }

    @Test
    void saveToDo() {
        ToDoDto toDoDto = Creator.createToDoDto();
        ToDo toDo = service.saveToDo(toDoDto);

        Assertions.assertEquals(toDo.getId(), Creator.TODO_ID);
        Assertions.assertEquals(toDo.getTask(), Creator.TODO_TASK);
        Assertions.assertNotNull(toDo.getCreatedAt());
    }

    @Test
    void updateToDo() throws Exception {
        ToDo updatedToDo = Creator.createToDo();
        updatedToDo.setTask(Creator.TODO_UPDATED);
        updatedToDo.setUpdatedAt(Creator.TODO_CREATEDAT);

        ToDo toDo = service.updateToDo(updatedToDo.getId(), new ToDoDto());

        Assertions.assertEquals(toDo.getId(), Creator.TODO_ID);
        Assertions.assertEquals(toDo.getTask(), Creator.TODO_UPDATED);
        Assertions.assertNotNull(toDo.getUpdatedAt());
    }

    @Test
    void deleteToDo() throws Exception {
        service.deleteToDo(Creator.TODO_ID);

        org.assertj.core.api.Assertions
                .assertThatCode(() -> service.deleteToDo(new ObjectId().toString()))
                .doesNotThrowAnyException();
    }
}