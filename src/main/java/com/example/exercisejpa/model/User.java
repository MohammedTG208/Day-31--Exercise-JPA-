package com.example.exercisejpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "the id is requirement and most be positive")
    private Integer id;
    @Column(columnDefinition = "varchar(25) not null")
    @NotNull(message = "User name must not be empty")
    @Size(max = 25, message = "User name have to be less than 25 length long")
    private String username;
    @Column(columnDefinition = "Varchar(20) not null")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" ,message = "Minimum eight characters, at least one letter, one number and one special character")
    private String password;
//    @Column(columnDefinition = "varchar not null unique")
//    @Column(columnDefinition = "varchar not null")
    @Column(unique = true, nullable = false)
    @Email(message = "enter valid email")
    private String email;
//    @Column(columnDefinition = "Varchar not null check(role='admin' or role='customer')")
    @Column(nullable = false)
    @Pattern(regexp = "(admin|customer)+$",message = "The role most be Admin or customer")
    private String role;
    @Column(columnDefinition = "int not null")
    @Positive(message = "balance must not be empty, have to be positive")
    private int balance;
}
