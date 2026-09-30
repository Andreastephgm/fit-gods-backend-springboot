package com.example.fit_gods_springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//Anotaciones de lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//anotaciones bd
@Entity
@Table(name = "progress")
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProgress")
//Atributos
    private Long idProgress;

    private LocalDate date;
    private Double weight;
    private String notes;
//Relación: Un usuario puede tener muchos registros de progreso pero un progreso solo tiene un usuario.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
}
