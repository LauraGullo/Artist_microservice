package com.example.demo;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
@EnableEurekaClient

@SpringBootApplication
public class SecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}

/*
		 @Bean
		 CommandLineRunner run(UserService userService){
         return args -> {
            userService.addUser(new User(null,"Gaby", "1234",null));
            userService.addUser(new User(null,"Caro", "1234",null));
			userService.addUser(new User(null,"Lau", "1234",null));

            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMINISTRATOR"));

            userService.addRoleToUser("Lau", "ROLE_USER");
            userService.addRoleToUser("Caro", "ROLE_ADMINISTRATOR");
			userService.addRoleToUser("Gaby", "ROLE_USER");
		};
    }*/
}
