package com.br.completedToDo.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Task {
    @Id
    private String id;
    private String task;
    private boolean completedTask;
    private Date createdAt;
    private Date updatedAt;
}
