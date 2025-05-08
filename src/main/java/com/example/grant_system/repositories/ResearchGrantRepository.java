package com.example.grant_system.repositories;

import com.example.grant_system.model.ResearchGrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchGrantRepository extends JpaRepository<ResearchGrant, Long> {
    // You can define custom query methods here if needed
}
