package com.example.fit_gods_springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Anotaciones de Lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

// Anotaciones de JPA / Base de datos
@Entity
@Table(name = "routineExercises")
public class RoutineExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoutineExercise") // Primary Key
    private Long idRoutineExercise;

    @Column(name = "exerciseName", nullable = false)
    private String exerciseName;

    private Long idExercise;
    private Integer series;
    private Integer repetitions;
    private Integer rest;

    // Relación ManyToOne: Muchas ejecuciones de ejercicio pertenecen a una Rutina
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRoutine", nullable = false)
    private Routine routine;
}