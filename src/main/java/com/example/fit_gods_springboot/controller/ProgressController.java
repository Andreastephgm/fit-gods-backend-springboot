package com.example.fit_gods_springboot.controller;

import com.example.fit_gods_springboot.model.Progress;
import com.example.fit_gods_springboot.service.ProgressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/new")
    public ResponseEntity<Progress> save(@RequestBody Progress progress) {
        Progress newProgress = progressService.save(progress);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProgress);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Progress>> findAll() {
        return ResponseEntity.ok(progressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Progress> findById(@PathVariable Long id) {
        return progressService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Progress>> findByUserId(@PathVariable Long idUser) {
        List<Progress> progressList = progressService.findByUserId(idUser);
        return ResponseEntity.ok(progressList);
    }

    // Endpoint PUT para solucionar el "Method 'PUT' is not supported"
    @PutMapping("/update/{id}")
    public ResponseEntity<Progress> update(@PathVariable Long id, @RequestBody Progress progressDetails) {
        return progressService.findById(id).map(existingProgress -> {
            existingProgress.setDate(progressDetails.getDate());
            existingProgress.setWeight(progressDetails.getWeight());
            existingProgress.setNotes(progressDetails.getNotes());

            if (progressDetails.getUser() != null) {
                existingProgress.setUser(progressDetails.getUser());
            }

            Progress updatedProgress = progressService.update(existingProgress);
            return ResponseEntity.ok(updatedProgress);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (progressService.findById(id).isPresent()) {
            progressService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}