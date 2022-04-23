package com.br.completedToDo.service;

import com.br.completedToDo.domain.AppUser;
import com.br.completedToDo.domain.Role;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
