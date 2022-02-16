package software.sport;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class SportController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/user")
    public void addStudent(@RequestBody User user ){
        userRepo.save(user);
    }

    @GetMapping("/{email}")
    public Boolean giveStatus(@PathVariable("email") String email){

         User user = userRepo.findByEmail(email);
         
         if (user != null){

            System.out.println(user.getStatus());
            return user.getStatus();
         }
       
        return null;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

   

    
}
