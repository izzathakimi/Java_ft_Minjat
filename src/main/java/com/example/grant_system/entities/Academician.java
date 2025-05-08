package com.example.grant_system.entities;
import java.util.List;

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

    @ManyToMany(mappedBy = "projectMembers")
    private List<ResearchGrant> researchGrants;
    
    @OneToMany(mappedBy = "projectLeader")
    private List<ResearchGrant> grantsLed;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String faculty;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false, unique = true)
    private String email;
}
