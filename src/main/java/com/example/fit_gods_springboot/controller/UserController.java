package com.example.fit_gods_springboot.controller;

import com.example.fit_gods_springboot.model.User;
import com.example.fit_gods_springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Registrar un nuevo usuario
    // POST: http://localhost:8080/api/users/new
    @PostMapping("/new")
    public ResponseEntity<User> save(@RequestBody User user) {
        User newUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // Listar todos los usuarios
    // GET: http://localhost:8080/api/users/all
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // Buscar un usuario por ID
    // GET: http://localhost:8080/api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar un usuario existente
    // PUT: http://localhost:8080/api/users/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setSurname(userDetails.getSurname());
            user.setHeight(userDetails.getHeight());
            user.setEmail(userDetails.getEmail());
            user.setDateOfBirth(userDetails.getDateOfBirth());
            user.setWeight(userDetails.getWeight());
            user.setPassword(userDetails.getPassword());
            user.setObjective(userDetails.getObjective());

            User userUpdate = userService.save(user);
            return ResponseEntity.ok(userUpdate);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un usuario por ID
    // DELETE: http://localhost:8080/api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (userService.findById(id).isPresent()) {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}