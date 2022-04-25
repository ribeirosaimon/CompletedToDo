package com.br.completedToDo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CompletedToDoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompletedToDoApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            userService.saveRole(new Role(new ObjectId().toString(), "ROLE_USER"));
//            userService.saveRole(new Role(new ObjectId().toString(), "ROLE_MANAGER"));
//            userService.saveRole(new Role(new ObjectId().toString(), "ROLE_ADMIN"));
//            userService.saveRole(new Role(new ObjectId().toString(), "ROLE_SUPER_ADMIN"));
//
//            userService.saveUser(new AppUser(new ObjectId().toString(), "Saimon Ribeiro", "saimon", "123", new ArrayList<>()));
//
//            userService.addRoleToUser("saimon", "ROLE_SUPER_ADMIN");
//        };
//    }

}
