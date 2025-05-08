package com.example.grant_system.controllers;

import com.example.grant_system.entities.Academician;
import com.example.grant_system.repositories.AcademicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/academicians")
public class AcademicianController {

    @Autowired
    private AcademicianRepository academicianRepo;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("academicians", academicianRepo.findAll());
        return "academicians/index";  // -> templates/academicians/index.html
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("academician", new Academician());
        return "academicians/create";
    }

    @PostMapping
    public String save(@ModelAttribute Academician academician) {
        academicianRepo.save(academician);
        return "redirect:/academicians";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("academician", academicianRepo.findById(id).orElseThrow());
        return "academicians/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Academician updated) {
        updated.setId(id);
        academicianRepo.save(updated);
        return "redirect:/academicians";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        academicianRepo.deleteById(id);
        return "redirect:/academicians";
    }
}
