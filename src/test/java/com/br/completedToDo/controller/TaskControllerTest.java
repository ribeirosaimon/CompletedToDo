package com.br.completedToDo.controller;

import com.br.completedToDo.model.entity.Task;
import com.br.completedToDo.payload.ToDoDto;
import com.br.completedToDo.security.ApplicationUserRole;
import com.br.completedToDo.service.TaskService;
import com.br.completedToDo.util.Creator;
import com.br.completedToDo.util.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@DisplayName("ToDo Controller Test")
@WebMvcTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    TaskService service;


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
    void simpleTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Creator.TODO_URL.concat("/" + Creator.TODO_ID))
                .accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Get ToDo Controller")
    void getToDo() throws Exception {

        MockHttpServletRequestBuilder request = new HttpRequest(ApplicationUserRole.ADMIN).makeGetRequest();

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(Creator.TODO_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("task").value(Creator.TODO_TASK))
                .andExpect(MockMvcResultMatchers.jsonPath("createdAt").exists());
    }

    @Test
    @DisplayName("Post ToDo Controller")
    void saveToDo() throws Exception {
        ToDoDto toDoDto = Creator.createToDoDto();

        String json = new ObjectMapper().writeValueAsString(toDoDto);
        MockHttpServletRequestBuilder request = HttpRequest.makePostRequest(json);

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(Creator.TODO_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("task").value(Creator.TODO_TASK))
                .andExpect(MockMvcResultMatchers.jsonPath("createdAt").exists());
    }

    @Test
    @DisplayName("Update ToDo Controller")
    void updateToDo() throws Exception {
        ToDoDto toDoDto = Creator.createToDoDto();
        toDoDto.setTask(Creator.TODO_UPDATED);


        String json = new ObjectMapper().writeValueAsString(toDoDto);
        MockHttpServletRequestBuilder request = HttpRequest.makePutRequest(json);

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(Creator.TODO_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("task").value(Creator.TODO_UPDATED))
                .andExpect(MockMvcResultMatchers.jsonPath("createdAt").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("updatedAt").exists());
    }

    @Test
    @DisplayName("Delete ToDo Controller")
    void deleteToDo() throws Exception {
        MockHttpServletRequestBuilder request = HttpRequest.makeDeleteRequest();
        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}