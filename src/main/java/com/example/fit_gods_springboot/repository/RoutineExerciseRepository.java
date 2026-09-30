package com.example.fit_gods_springboot.repository;

import com.example.fit_gods_springboot.model.RoutineExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineExerciseRepository extends JpaRepository<RoutineExercise, Long> {

    //Buscar todos los ejercicios asociados a una rutina por su ID
    List<RoutineExercise> findByRoutineIdRoutine(Long idRoutine);

    //Buscar ejercicios por ID de rutina y por ID de ejercicio
    List<RoutineExercise> findByRoutineIdRoutineAndIdExercise(Long idRoutine, Long idExercise);
}
