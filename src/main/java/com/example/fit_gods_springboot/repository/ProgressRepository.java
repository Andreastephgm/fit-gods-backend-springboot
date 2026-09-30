package com.example.fit_gods_springboot.repository;

import com.example.fit_gods_springboot.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    // Método personalizado para buscar registros de progreso por el idUser del objeto User asociado
    List<Progress> findByUserId(Long idUser);
}
