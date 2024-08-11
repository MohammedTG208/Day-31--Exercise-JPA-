package com.example.exercisejpa.Service;

import com.example.exercisejpa.Repository.CategoryRepository;
import com.example.exercisejpa.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public boolean deleteCategory(int id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateCategory(Category category,Integer id) {
        if (categoryRepository.existsById(id)) {
            Category oldCategory = categoryRepository.getById(id);
            oldCategory.setName(category.getName());
            categoryRepository.save(oldCategory);
            return true;
        }
        return false;
    }
}
