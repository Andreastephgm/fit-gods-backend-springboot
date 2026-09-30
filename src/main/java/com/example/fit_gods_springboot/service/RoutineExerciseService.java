package com.example.fit_gods_springboot.service;

import com.example.fit_gods_springboot.model.RoutineExercise;
import com.example.fit_gods_springboot.repository.RoutineExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoutineExerciseService {

    private final RoutineExerciseRepository routineExerciseRepository;

    public RoutineExerciseService(RoutineExerciseRepository routineExerciseRepository) {
        this.routineExerciseRepository = routineExerciseRepository;
    }

    // Guardar o actualizar un ejercicio de rutina
    public RoutineExercise save(RoutineExercise routineExercise) {
        return routineExerciseRepository.save(routineExercise);
    }

    // Listar todos los ejercicios de rutina
    public List<RoutineExercise> findAll() {
        return routineExerciseRepository.findAll();
    }

    // Buscar por ID del registro (idRoutineExercise)
    public Optional<RoutineExercise> findById(Long id) {
        return routineExerciseRepository.findById(id);
    }

    // Listar todos los ejercicios asociados a una rutina
    public List<RoutineExercise> findByRoutineId(Long idRoutine) {
        return routineExerciseRepository.findByRoutineIdRoutine(idRoutine);
    }

    // Buscar por ID de rutina e ID de ejercicio
    public List<RoutineExercise> findByRoutineAndExercise(Long idRoutine, Long idExercise) {
        return routineExerciseRepository.findByRoutineIdRoutineAndIdExercise(idRoutine, idExercise);
    }

    // Eliminar por ID del registro
    public void deleteById(Long id) {
        routineExerciseRepository.deleteById(id);
    }
}
