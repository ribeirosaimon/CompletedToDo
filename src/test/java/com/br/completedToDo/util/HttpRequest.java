package com.br.completedToDo.util;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class HttpRequest {

    private final ApplicationUserRole applicationUserRole;

    public static String login;
    public static String password;

    public HttpRequest(ApplicationUserRole applicationUserRole) {
        this.applicationUserRole = applicationUserRole;

        if (applicationUserRole.name().equals(ApplicationUserRole.USER)) {
            this.password = "user";
            this.login = "user";
        } else {
            this.password = "admin";
            this.login = "admin";
        }
    }

    public MockHttpServletRequestBuilder makeGetRequest() {
        return MockMvcRequestBuilders
                .get(Creator.TODO_URL.concat("/" + Creator.TODO_ID))
                .header("username", this.login)
                .header("password", this.password)
                .header("role", this.applicationUserRole)
                .accept(MediaType.APPLICATION_JSON);
    }

    public static MockHttpServletRequestBuilder makeSimpleRequest(){
        return MockMvcRequestBuilders
                .get(Creator.TODO_URL.concat("/"+ Creator.TODO_ID))
                .accept(MediaType.APPLICATION_JSON);
    }

    public static MockHttpServletRequestBuilder makePostRequest(String body) {
        return MockMvcRequestBuilders
                .post(Creator.TODO_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(body);
    }

    public static MockHttpServletRequestBuilder makePutRequest(String body) {
        return MockMvcRequestBuilders
                .put(Creator.TODO_URL.concat("/" + Creator.TODO_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(body);
    }

    public static MockHttpServletRequestBuilder makeDeleteRequest() {
        return MockMvcRequestBuilders
                .delete(Creator.TODO_URL.concat("/" + Creator.TODO_ID))
                .accept(MediaType.APPLICATION_JSON);
    }

}
