package com.example.grant_system.controllers;

import com.example.grant_system.entities.Milestone;
import com.example.grant_system.entities.ResearchGrant;
import com.example.grant_system.repositories.MilestoneRepository;
import com.example.grant_system.repositories.ResearchGrantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/milestones")
public class MilestoneController {

    @Autowired
    private MilestoneRepository milestoneRepo;

    @Autowired
    private ResearchGrantRepository grantRepo;

    @GetMapping
    public String listMilestones(Model model) {
        model.addAttribute("milestones", milestoneRepo.findAll());
        return "milestones/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("milestone", new Milestone());
        model.addAttribute("grants", grantRepo.findAll());
        return "milestones/create";
    }

    @PostMapping
    public String saveMilestone(@ModelAttribute Milestone milestone) {
        milestone.setDateUpdated(LocalDateTime.now());
        milestoneRepo.save(milestone);
        return "redirect:/milestones";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Milestone milestone = milestoneRepo.findById(id).orElseThrow();
        model.addAttribute("milestone", milestone);
        model.addAttribute("grants", grantRepo.findAll());
        return "milestones/edit";
    }

    @PostMapping("/update/{id}")
    public String updateMilestone(@PathVariable Long id, @ModelAttribute Milestone milestone) {
        milestone.setId(id);
        milestone.setDateUpdated(LocalDateTime.now());
        milestoneRepo.save(milestone);
        return "redirect:/milestones";
    }

    @GetMapping("/delete/{id}")
    public String deleteMilestone(@PathVariable Long id) {
        milestoneRepo.deleteById(id);
        return "redirect:/milestones";
    }

    @GetMapping("/view/{id}")
    public String viewMilestone(@PathVariable Long id, Model model) {
        Milestone milestone = milestoneRepo.findById(id).orElseThrow();
        model.addAttribute("milestone", milestone);
        return "milestones/view";
    }
}
