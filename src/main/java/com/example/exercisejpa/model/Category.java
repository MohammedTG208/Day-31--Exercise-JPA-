package com.example.exercisejpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "id is requirement and be Positive number")
    private Integer id;
    @NotNull(message = "name is requirement")
    @Size(max = 20,message = "max char for name is 20")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String name;
}
