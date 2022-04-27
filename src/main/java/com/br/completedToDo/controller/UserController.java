package com.br.completedToDo.controller;

import com.br.completedToDo.domain.AppUser;
import com.br.completedToDo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.refreshToken(request, response);
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/api/signup")
    public ResponseEntity signup(@RequestBody AppUser user){
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/signup")
                .toString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

}
