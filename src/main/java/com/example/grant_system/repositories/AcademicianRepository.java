package com.example.grantsystem.repositories;

import com.example.grantsystem.entities.Academician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicianRepository extends JpaRepository<Academician, Long> {
    // Optional: add custom query methods here
}
