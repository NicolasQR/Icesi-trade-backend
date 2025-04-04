package com.example.icesitrade.controller;

import com.example.icesitrade.model.Rating;
import com.example.icesitrade.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PreAuthorize("hasAuthority('rating:write')")
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        return ResponseEntity.ok(ratingService.createRating(rating));
    }

    @PreAuthorize("hasAuthority('rating:read')")
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @PreAuthorize("hasAuthority('rating:read')")
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Rating>> getRatingsForUser(@PathVariable Long id) {
        return ResponseEntity.ok(ratingService.getRatingsForUser(id));
    }

}
