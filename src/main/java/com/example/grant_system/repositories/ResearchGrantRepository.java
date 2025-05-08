package com.example.grant_system.repositories;

import com.example.grant_system.entities.ResearchGrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResearchGrantRepository extends JpaRepository<ResearchGrant, Long> {
    // You can define custom query methods here if needed

    @Query("SELECT r FROM ResearchGrant r JOIN r.projectMembers m WHERE m.id = :academicianId")
    List<ResearchGrant> findByProjectMemberId(@Param("academicianId") Long academicianId);

    List<ResearchGrant> findByProjectLeaderId(Long projectLeaderId);
}
