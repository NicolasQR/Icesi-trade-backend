package com.example.icesitrade.service;

import com.example.icesitrade.model.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating(Rating rating);
    List<Rating> getRatingsForUser(Long reviewedId);
    List<Rating> getAllRatings();

}
