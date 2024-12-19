package com.example.grocery_booking.config;

import com.example.grocery_booking.enums.UserRole;
import com.example.grocery_booking.model.User;
import com.example.grocery_booking.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            //creating admin user
            if (userRepository.findByUsername("admin").isEmpty()) {
                userRepository.save(User.builder().username("admin").password("admin123").userRole(UserRole.ADMIN).build());
            }

            //creating regular user
            if (userRepository.findByUsername("user").isEmpty()) {
                userRepository.save(User.builder().username("user").password("user123").userRole(UserRole.USER).build());
            }
            System.out.println("Initializing users...");
        };
    }
}
