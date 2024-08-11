package com.example.exercisejpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MerchantStock {
    @Positive(message = "the id is requirement")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Positive(message = "the productid is requirement and most be positive number")
    @Column(columnDefinition = "int not null unique")
    private int productid;
    @Positive(message = "merchant id is requirement and most be positive number")
    @Column(columnDefinition = "int not null ")
    private int merchantid;
    @Positive(message = "stock is requirement and most be positive number")
    @Column(columnDefinition = "int not null")
    private int stock;
}
