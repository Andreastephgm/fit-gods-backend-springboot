package com.example.fit_gods_springboot.controller;

import com.example.fit_gods_springboot.model.RoutineExercise;
import com.example.fit_gods_springboot.service.RoutineExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routine-exercises")
@CrossOrigin(origins = "*")
public class RoutineExerciseController {

    private final RoutineExerciseService routineExerciseService;

    public RoutineExerciseController(RoutineExerciseService routineExerciseService) {
        this.routineExerciseService = routineExerciseService;
    }

    // Agregar un ejercicio a una rutina
    // POST: http://localhost:8080/api/routine-exercises/new
    @PostMapping("/new")
    public ResponseEntity<RoutineExercise> save(@RequestBody RoutineExercise routineExercise) {
        RoutineExercise newRecord = routineExerciseService.save(routineExercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRecord);
    }

    // Listar todos los ejercicios de todas las rutinas
    // GET: http://localhost:8080/api/routine-exercises/all
    @GetMapping("/all")
    public ResponseEntity<List<RoutineExercise>> findAll() {
        return ResponseEntity.ok(routineExerciseService.findAll());
    }

    // Buscar un registro por su ID principal (idRoutineExercise)
    // GET: http://localhost:8080/api/routine-exercises/{id}
    @GetMapping("/{id}")
    public ResponseEntity<RoutineExercise> findById(@PathVariable Long id) {
        return routineExerciseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar los ejercicios pertenecientes a una rutina específica
    // GET: http://localhost:8080/api/routine-exercises/routine/{idRoutine}
    @GetMapping("/routine/{idRoutine}")
    public ResponseEntity<List<RoutineExercise>> findByRoutineId(@PathVariable Long idRoutine) {
        return ResponseEntity.ok(routineExerciseService.findByRoutineId(idRoutine));
    }

    // Actualizar datos del ejercicio en la rutina (series, reps, descanso)
    // PUT: http://localhost:8080/api/routine-exercises/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<RoutineExercise> update(@PathVariable Long id, @RequestBody RoutineExercise details) {
        return routineExerciseService.findById(id).map(record -> {
            record.setExerciseName(details.getExerciseName());
            record.setIdExercise(details.getIdExercise());
            record.setSeries(details.getSeries());
            record.setRepetitions(details.getRepetitions());
            record.setRest(details.getRest());
            if (details.getRoutine() != null) {
                record.setRoutine(details.getRoutine());
            }

            RoutineExercise updated = routineExerciseService.save(record);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un ejercicio de una rutina por su ID
    // DELETE: http://localhost:8080/api/routine-exercises/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (routineExerciseService.findById(id).isPresent()) {
            routineExerciseService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}