package com.br.completedToDo.domain;


import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class AppUser {
    @Id
    private String id;
    private String name;
    @Unique
    private String username;
    private String password;
    private Collection<Role> roles = new ArrayList<>();


}
