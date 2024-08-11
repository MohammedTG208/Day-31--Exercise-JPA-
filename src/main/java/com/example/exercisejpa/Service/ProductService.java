package com.example.exercisejpa.Service;

import com.example.exercisejpa.Repository.ProductRepository;
import com.example.exercisejpa.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public boolean deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateProduct(Product product,Integer id) {
        if (productRepository.existsById(id)) {
            Product oldProduct = productRepository.getById(id);
            oldProduct.setName(product.getName());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setCategoryID(product.getCategoryID());
            productRepository.save(oldProduct);
            return true;
        }
        return false;
    }
}
