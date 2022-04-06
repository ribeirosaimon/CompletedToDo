package com.br.completedToDo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
//@EnableConfigurationProperties(AppProperties.class)
public class CompletedToDoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompletedToDoApplication.class, args);
    }

}
