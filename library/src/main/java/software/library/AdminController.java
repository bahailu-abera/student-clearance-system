package software.library;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/")
    public String getHome(Model model)
    {
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);

        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/admin")
    public String adminHome(Model model) {
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);

        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/admin/addStudent")
    public String getForm(Model model) {
        model.addAttribute("user", new User());
        return "registerStudent";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(User user) {

        user.setRoles("ROLE_STUDENT");
        userRepo.save(user);
    
        return "redirect:/admin";

    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, User user,Model model) {
    
        user.setRoles("ROLE_STUDENT");
        userRepo.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepo.delete(user);
        return "redirect:/admin";
    }

}
