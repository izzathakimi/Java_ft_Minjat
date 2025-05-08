package com.example.grant_system.controllers;

import com.example.grant_system.entities.ResearchGrant;
import com.example.grant_system.repositories.ResearchGrantRepository;
import com.example.grant_system.repositories.AcademicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/grants")
public class ResearchGrantController {

    @Autowired
    private ResearchGrantRepository grantRepo;
    
    @Autowired
    private AcademicianRepository academicianRepo;

    @GetMapping
    public String listGrants(Model model) {
        List<ResearchGrant> grants = grantRepo.findAll();
        model.addAttribute("grants", grants);
        return "grants/index"; // Create this Thymeleaf file
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("grant", new ResearchGrant());
        model.addAttribute("academicians", academicianRepo.findAll());
        return "grants/create";
    }

    @PostMapping
    public String saveGrant(@ModelAttribute ResearchGrant grant) {
        grantRepo.save(grant);
        return "redirect:/grants";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ResearchGrant grant = grantRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid grant ID: " + id));
        System.out.println("Editing grant with ID: " + grant.getId());
        
        model.addAttribute("grant", grant);
        model.addAttribute("academicians", academicianRepo.findAll());
        return "grants/edit";
    }

    @PostMapping("/update/{id}")
    public String updateGrant(@PathVariable Long id, @ModelAttribute ResearchGrant grant) {
        grant.setId(id);
        grantRepo.save(grant);
        return "redirect:/grants";
    }

    @GetMapping("/delete/{id}")
    public String deleteGrant(@PathVariable Long id) {
        grantRepo.deleteById(id);
        return "redirect:/grants";
    }

    @GetMapping("/view/{id}")
    public String viewGrant(@PathVariable Long id, Model model) {
        ResearchGrant grant = grantRepo.findById(id).orElseThrow();
        model.addAttribute("grant", grant);
        return "grants/view";
    }
}
