package software.registrar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrarController {

    @Autowired
    private UserRepository userRepo;
    

    @PutMapping("/setStatus/{email}")
    public String saveClearanceStatus(@PathVariable("email") String email, @RequestBody User patch){

        User user = userRepo.findByEmail(email);
        if (user != null)
        {
            user.setStatus(patch.getStatus());
            user.setReason(patch.getReason());
            userRepo.save(user);

            return "updated";
        }
        return "approved";
    }

    }
    
