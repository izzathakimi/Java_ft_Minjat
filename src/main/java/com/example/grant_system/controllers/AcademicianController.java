package com.example.grant_system.controllers;

import com.example.grant_system.entities.Academician;
import com.example.grant_system.repositories.AcademicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;
import com.example.grant_system.repositories.UserRepository; // Add this import
import com.example.grant_system.entities.User;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage; // Add this import
import java.util.List; // Import List
import com.example.grant_system.entities.ResearchGrant; // Import Grant entity
import com.example.grant_system.repositories.ResearchGrantRepository; // Import GrantRepository

@Controller
@RequestMapping("/academicians")
public class AcademicianController {


    @Autowired
    private UserRepository UserRepository; // Add this line

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AcademicianRepository academicianRepo;

    @Autowired
    private JavaMailSender mailSender;  

    @Autowired
    private ResearchGrantRepository researchGrantRepo; // Inject the repository

    private void sendWelcomeEmail(String to, String rawPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your Academician Account");
        message.setText("Hello,\n\nYour account has been created.\nEmail: " + to +
                        "\nPassword: " + rawPassword +
                        "\n\nPlease change your password after login.\n\n- Grant System");
        mailSender.send(message);
    }


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
        // Step 1: Save the academician
        academicianRepo.save(academician);

        // Step 2: Generate random password
        String rawPassword = UUID.randomUUID().toString().substring(0, 8);

        // Step 3: Hash the password
        String hashedPassword = passwordEncoder.encode(rawPassword); // use injected bean

        // Step 4: Create and save the user
        User user = new User();
        user.setEmail(academician.getEmail());
        user.setName(academician.getName());
        user.setPassword(hashedPassword);
        user.setUserLevel("ACADEMICIAN");

        UserRepository.save(user);

        // Step 5: Send the plain password via email
        sendWelcomeEmail(academician.getEmail(), rawPassword);

        return "redirect:/academicians";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Academician academician = academicianRepo.findById(id).orElseThrow();
        model.addAttribute("academician", academician);
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

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Academician academician = academicianRepo.findById(id).orElseThrow();
    

            // ✅ Test: Fetch ALL grants (temporarily)
        List<ResearchGrant> allGrants = researchGrantRepo.findAll();
        System.out.println("✅ Total grants in DB: " + allGrants.size());
        for (ResearchGrant g : allGrants) {
            System.out.println("Grant in DB -> ID: " + g.getId() + ", Title: " + g.getTitle() + ", Leader ID: " + g.getProjectLeader().getId());
        }

        List<ResearchGrant> projectsLed = researchGrantRepo.findByProjectLeaderId(id);
    
        // 🔍 Step 2: Debugging logs
        System.out.println("Viewing academician ID: " + id);
        System.out.println("Grants found: " + projectsLed.size());


        List<ResearchGrant> projectsInvolved = researchGrantRepo.findByProjectMemberId(id);

        model.addAttribute("academician", academician);
        model.addAttribute("projectsLed", projectsLed);
        model.addAttribute("projectsInvolved", projectsInvolved);

        return "academicians/view";
    }
    

    public String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 8); // 8-char password
    }
    
}
