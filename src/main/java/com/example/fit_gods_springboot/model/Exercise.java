package com.example.fit_gods_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Exercise {

    private Long idExercise;
    private String name;
    private  String description;
    private String muscularGroup;
}
