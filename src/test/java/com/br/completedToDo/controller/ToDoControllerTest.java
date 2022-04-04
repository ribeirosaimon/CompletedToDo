package com.br.completedToDo.controller;

import com.br.completedToDo.model.entity.ToDo;
import com.br.completedToDo.payload.ToDoDto;
import com.br.completedToDo.service.ToDoService;
import com.br.completedToDo.util.Creator;
import com.br.completedToDo.util.HttpRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@DisplayName("ToDo Controller Test")
@WebMvcTest(excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@AutoConfigureMockMvc
class ToDoControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ToDoService service;


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
    @DisplayName("Get ToDo Controller")
    void getToDo() throws Exception {
        MockHttpServletRequestBuilder request = HttpRequest.makeGetRequest();

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