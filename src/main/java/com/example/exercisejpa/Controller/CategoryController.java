package com.example.exercisejpa.Controller;

import com.example.exercisejpa.Service.CategoryService;
import com.example.exercisejpa.model.Category;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;
    @GetMapping("/getall")
    public ResponseEntity getAllCategory() {
        return categoryService.getAll().isEmpty()?ResponseEntity.status(400).body("The DB is Empty"):ResponseEntity.status(200).body(categoryService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            categoryService.addCategory(category);
            return ResponseEntity.status(201).body("added successfully");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            if (categoryService.updateCategory(category,id)){
                return ResponseEntity.status(201).body("updated successfully");
            }
            return ResponseEntity.status(400).body("update failed");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        if (categoryService.deleteCategory(id)){
            return ResponseEntity.status(201).body("deleted successfully");
        }
        return ResponseEntity.status(400).body("delete failed");
    }
}
