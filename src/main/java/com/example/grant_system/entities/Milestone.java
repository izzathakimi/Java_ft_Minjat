package com.example.grant_system.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate targetCompletionDate;

    private String deliverable;

    private String status;

    private String remark;

    @ManyToOne
    @JoinColumn(name = "research_grant_id")
    private ResearchGrant researchGrant;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getTargetCompletionDate() { return targetCompletionDate; }
    public void setTargetCompletionDate(LocalDate targetCompletionDate) { this.targetCompletionDate = targetCompletionDate; }

    public String getDeliverable() { return deliverable; }
    public void setDeliverable(String deliverable) { this.deliverable = deliverable; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public ResearchGrant getResearchGrant() { return researchGrant; }
    public void setResearchGrant(ResearchGrant researchGrant) { this.researchGrant = researchGrant; }
}
