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
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "id is requirement and be positive number")
    private Integer id;
    @NotNull(message = "name is requirement")
    @Size(max = 20,message = "name most be less than 20 length long")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @Positive(message = "price is requirement and most be positive")
    @Column(columnDefinition = "int not null")
    private int price;
    @Positive(message = "categoryID is requirement and most be positive")
    @Column(columnDefinition = "int not null")
    private int categoryID;
}
