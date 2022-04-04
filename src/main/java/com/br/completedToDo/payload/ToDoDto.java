package com.br.completedToDo.payload;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToDoDto {

    @NotNull
    @NotBlank
    private String task;

}
