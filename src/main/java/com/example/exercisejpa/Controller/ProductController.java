package com.example.exercisejpa.Controller;

import com.example.exercisejpa.Service.ProductService;
import com.example.exercisejpa.model.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/getall")
    public ResponseEntity getAllProducts() {
        return productService.getAllProduct().isEmpty()?ResponseEntity.status(400).body("The Proudest DB Empty"):ResponseEntity.ok(productService.getAllProduct());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            productService.addProduct(product);
            return ResponseEntity.status(201).body("Product added successfully");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id,@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            if (productService.updateProduct(product,id)) {
                return ResponseEntity.status(201).body("Product updated successfully");
            }
            return ResponseEntity.status(404).body("Product not found");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.status(201).body("Product deleted successfully");
        }
        return ResponseEntity.status(404).body("Product not found");
    }
}
