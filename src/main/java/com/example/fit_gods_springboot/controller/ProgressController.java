package com.example.fit_gods_springboot.controller;

import com.example.fit_gods_springboot.model.Progress;
import com.example.fit_gods_springboot.service.ProgressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Anotación para indicar que esta clase define controladores REST y serializa las respuestas automáticamente a JSON
@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    // Inyección de dependencias de la capa de servicio
    private final ProgressService progressService;

    // Constructor para realizar la inyección de la dependencia de ProgressService
    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    // Endpoint para registrar un nuevo avance/progreso
    // POST: http://localhost:8080/api/progress/new
    @PostMapping("/new")
    public ResponseEntity<Progress> save(@RequestBody Progress progress) {
        // Guarda el nuevo registro de progreso enviado en el body de la petición
        Progress newProgress = progressService.save(progress);
        // Retorna el objeto creado con el estado HTTP 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(newProgress);
    }

    // Endpoint para obtener el historial completo de progresos
    // GET: http://localhost:8080/api/progress/allProgress
    @GetMapping("/allProgress")
    public ResponseEntity<List<Progress>> findAll() {
        // Consulta todos los progresos registrados y responde con estado 200 OK
        return ResponseEntity.ok(progressService.findAll());
    }

    // Endpoint para consultar un progreso específico por su ID
    // GET: http://localhost:8080/api/progress/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Progress> findById(@PathVariable Long id) {
        // Busca el progreso por ID: si existe responde 200 OK, si no existe responde 404 Not Found
        return progressService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para consultar todos los registros de progreso pertenecientes a un usuario
    // GET: http://localhost:8080/api/progress/user/{id}
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Progress>> findByUserId(@PathVariable Long id) {
        // Retorna la lista de progresos asociados al idUser enviado en la URL
        return ResponseEntity.ok(progressService.findByUserId(id));
    }

    // Endpoint para actualizar un registro de progreso existente
    // POST: http://localhost:8080/api/progress/update/{id}
    @PostMapping("/update/{id}")
    public ResponseEntity<Progress> update(@PathVariable Long id, @RequestBody Progress progressDetails) {
        // Busca el progreso existente en la BD por su ID
        return progressService.findById(id).map(progress -> {
            // Actualiza las propiedades con los datos nuevos recibidos en el JSON
            progress.setDate(progressDetails.getDate());
            progress.setWeight(progressDetails.getWeight());
            progress.setNotes(progressDetails.getNotes());
            progress.setUser(progressDetails.getUser());

            // Guarda la entidad actualizada en la base de datos
            Progress progressUpdate = progressService.save(progress);

            // Responde con el objeto actualizado y estado 200 OK
            return ResponseEntity.ok(progressUpdate);
        }).orElse(ResponseEntity.notFound().build()); // Si no encuentra el registro, retorna 404 Not Found
    }

    // Endpoint para eliminar un registro de progreso por su ID
    // DELETE: http://localhost:8080/api/progress/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        // Verifica la existencia del recurso antes de proceder a la eliminación
        if (progressService.findById(id).isPresent()) {
            progressService.deleteById(id);
            // Retorna un estado 204 No Content indicando éxito sin cuerpo de respuesta
            return ResponseEntity.noContent().build();
        }
        // Retorna un estado 404 Not Found si el ID no existe en la base de datos
        return ResponseEntity.notFound().build();
    }
}
