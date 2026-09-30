package com.example.fit_gods_springboot.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

//Anotaciones de Lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//anotaciones bd
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")//primary key
    private Long id;
//atributos
    private String name;
    private String surname;
    private String email;
    private String password;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;
    private Double weight;
    private Double height;
    private String objective;

}
