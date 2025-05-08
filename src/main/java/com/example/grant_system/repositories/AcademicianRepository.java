package com.example.grant_system.repositories;

import com.example.grant_system.entities.Academician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicianRepository extends JpaRepository<Academician, Long> {
    // Optional: add custom query methods here
}
