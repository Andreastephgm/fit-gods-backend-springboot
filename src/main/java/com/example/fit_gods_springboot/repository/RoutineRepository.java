package com.example.fit_gods_springboot.repository;

import com.example.fit_gods_springboot.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {

    // Método personalizado para buscar rutinas por el idUser del objeto User asociado
    List<Routine> findByUserId(Long userId);
}
