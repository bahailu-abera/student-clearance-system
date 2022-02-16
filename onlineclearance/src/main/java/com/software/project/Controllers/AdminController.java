package com.software.project.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.software.project.security.EmailSenderService;
import com.software.project.security.User;
import com.software.project.security.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    public UserRepository userRepo;

    @Autowired
    private EmailSenderService senderService;

    @GetMapping("/adminDashBoard")
    public String adminHome(Model model) {
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);

        model.addAttribute("users", users);
        return "adminDashboard";
    }

    @GetMapping("/adminDashBoard/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("user", new User());
        return "registerStudent";
    }

    
    @PostMapping("/saveStudent")
    public String saveStudent(@Valid User newUser,Errors errors) {
        User user = userRepo.findByEmail(newUser.getEmail());
        
        if (user != null) {
            errors.rejectValue("username", null, "There is already an account registered with that email");
        }


        if(errors.hasErrors()) {
            return "registerStudent";
        }

        else{

            senderService.sendEmail(newUser.getEmail(), "AAiT online clearance ", "Hello Our AAiT online clearance System user" + " "+ newUser.getFirstName() +" "+ newUser.getLastName()+" " + "your password for the system is " + " "+ newUser.getPassword());

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(newUser.getPassword());

            newUser.setPassword(encodedPassword);
            newUser.setRoles("ROLE_STUDENT");
            newUser.setStatus(false);
            userRepo.save(newUser);
            return "redirect:/adminDashBoard";

        }


    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid User user,
            Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "edit";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles("ROLE_STUDENT");
        user.setStatus(false);
        userRepo.save(user);
        return "redirect:/adminDashBoard";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepo.delete(user);
        return "redirect:/adminDashBoard";
    }

}
