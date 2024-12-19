package com.example.grocery_booking.model;

import com.example.grocery_booking.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private UserRole userRole;
}
