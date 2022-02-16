package com.software.project;

import com.software.project.security.User;
import com.software.project.security.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SoftwareApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	// @Bean
	public void run(String... args) throws Exception {

		if (userRepo.findByRoles("ROLE_ADMIN") == null) {
			User admin = new User();
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.setUsername("admin");
			admin.setFirstName("admin");
			admin.setLastName("admin");
			admin.setEmail("admin12@gmail.com");
			admin.setRoles("ROLE_ADMIN");
			admin.setPhone("0900151289");
			userRepo.save(admin);
		}

	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(SoftwareApplication.class, args);
	}
	

}