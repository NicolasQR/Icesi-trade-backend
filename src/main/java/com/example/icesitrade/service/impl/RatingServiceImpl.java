package com.example.icesitrade.service.impl;

import com.example.icesitrade.model.Rating;
import com.example.icesitrade.repository.RatingRepository;
import com.example.icesitrade.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        rating.setTimestamp(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatingsForUser(Long reviewedId) {
        return ratingRepository.findByReviewedId(reviewedId);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

}
