package com.example.grant_system.repositories;

import com.example.grant_system.entities.ResearchGrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResearchGrantRepository extends JpaRepository<ResearchGrant, Long> {
    // You can define custom query methods here if needed
    List<ResearchGrant> findByProjectLeaderId(Long projectLeaderId);
}
