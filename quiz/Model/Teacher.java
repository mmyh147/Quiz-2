package com.example.quiz.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {

    @Min(value = 999, message = "id must contains 4 digits and not start with 0")
    private int id;
    @NotEmpty(message = "name must not be empty")
    private String name;
    @Min(value = 999, message = "salary must be 1000 or over")
    private double salary;


}
