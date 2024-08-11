package com.example.exercisejpa.Repository;

import com.example.exercisejpa.model.ProductReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewsRepository extends JpaRepository<ProductReviews, Integer> {
}
