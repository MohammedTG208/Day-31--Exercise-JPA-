package com.example.exercisejpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ProductReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "review id is requirement")
    private int review_id;
    @Positive(message = "product id is requirement")
    @Column(columnDefinition = "int not null")
    private int product_id;
    @Positive(message = "user id is requirement")
    @Column(columnDefinition = "int not null")
    private int user_id;
    @NotNull(message = "user name is requirement")
    private String user_name;
    @NotNull(message = "review text is requirement")
    private String review_text;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "Date not null")
    private LocalDate review_date;
    @NotNull(message = "role is requirement")

//    @Column(columnDefinition = "varchar(8) not null check( role0 = 'admin' OR role0 = 'customer')")
//    @Column(columnDefinition = "varchar not null")
//    @Column(nullable = false)
    @Column(columnDefinition = "Varchar(8) not null ")
    @Pattern(regexp = "(admin|customer)+$",message = "The role most be admin or customer")
    private String role;
}
