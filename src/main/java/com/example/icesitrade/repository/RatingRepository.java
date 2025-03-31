package com.example.icesitrade.repository;

import com.example.icesitrade.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByReviewedId(Long reviewedId);
}
