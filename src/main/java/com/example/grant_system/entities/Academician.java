package com.example.grantsystem.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "academicians")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Academician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String faculty;

    @Column(nullable = false, unique = true)
    private String email;
}
