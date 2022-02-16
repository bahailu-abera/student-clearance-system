package software.registrar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegistrarApplication {

	@Autowired
	UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(RegistrarApplication.class, args);
	}

	
	


	// @Override
	// public void run(String... args) throws Exception {

	// 	if (userRepo.findByRoles("ROLE_ADMIN") == null) {
	// 		User admin = new User();
	// 		admin.setUsername("admin");
	// 		admin.setFirstName("admin");
	// 		admin.setLastName("admin");
	// 		admin.setEmail("admin12@gmail.com");
	// 		admin.setRoles("ROLE_ADMIN");
	// 		admin.setPhone("0900151289");
	// 		userRepo.save(admin);
	// 	}

	// }


}
