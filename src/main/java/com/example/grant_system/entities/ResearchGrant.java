package com.example.grant_system.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ResearchGrant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private BigDecimal grantAmount;

    private String grantProvider;

    private LocalDate startDate;

    private int durationMonth;

    @ManyToOne
    @JoinColumn(name = "project_leader_id")
    private Academician projectLeader;

    @OneToMany(mappedBy = "researchGrant", cascade = CascadeType.ALL)
    private List<Milestone> milestones;

    // Relationships like members can be added later
    @ManyToMany
    @JoinTable(
        name = "grant_members",
        joinColumns = @JoinColumn(name = "grant_id"),
        inverseJoinColumns = @JoinColumn(name = "academician_id")
    )
    private List<Academician> projectMembers = new ArrayList<>();


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public BigDecimal getGrantAmount() { return grantAmount; }
    public void setGrantAmount(BigDecimal grantAmount) { this.grantAmount = grantAmount; }

    public String getGrantProvider() { return grantProvider; }
    public void setGrantProvider(String grantProvider) { this.grantProvider = grantProvider; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public int getDurationMonth() { return durationMonth; }
    public void setDurationMonth(int durationMonth) { this.durationMonth = durationMonth; }

    public Academician getProjectLeader() { return projectLeader; }
    public void setProjectLeader(Academician projectLeader) { this.projectLeader = projectLeader; }

    public List<Milestone> getMilestones() { return milestones; }
    public void setMilestones(List<Milestone> milestones) { this.milestones = milestones; }

    public List<Academician> getProjectMembers() {return projectMembers;}
    public void setProjectMembers(List<Academician> projectMembers) {this.projectMembers = projectMembers;}
    
}
