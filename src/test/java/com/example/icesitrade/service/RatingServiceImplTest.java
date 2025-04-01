package com.example.icesitrade.service;

import com.example.icesitrade.model.Rating;
import com.example.icesitrade.model.User;
import com.example.icesitrade.repository.RatingRepository;
import com.example.icesitrade.repository.UserRepository;
import com.example.icesitrade.service.impl.RatingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private NotificationService notificationService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRatingSuccess() {
        User reviewer = new User();
        reviewer.setId(2L);
        reviewer.setName("Nico User");

        User reviewed = new User();
        reviewed.setId(3L);
        reviewed.setName("Nico Seller");

        Rating rating = new Rating();
        rating.setReviewer(reviewer);
        rating.setReviewed(reviewed);
        rating.setScore(5);
        rating.setComment("Buen servicio");

        when(userRepository.findById(2L)).thenReturn(Optional.of(reviewer));
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);

        Rating result = ratingService.createRating(rating);

        assertEquals(5, result.getScore());
        assertEquals("Buen servicio", result.getComment());
    }


    @Test
    void testCreateRatingSelfReviewShouldFail() {
        User user = User.builder().id(1L).name("Mismo").build();

        Rating rating = Rating.builder()
                .score(4)
                .reviewer(user)
                .reviewed(user)
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ratingService.createRating(rating)
        );

        assertEquals("No puedes calificarte a ti mismo.", exception.getMessage());

    }

    @Test
    void testGetRatingsForUser() {
        Rating r1 = Rating.builder().id(1L).score(5).build();
        Rating r2 = Rating.builder().id(2L).score(3).build();

        when(ratingRepository.findByReviewedId(3L)).thenReturn(Arrays.asList(r1, r2));

        List<Rating> ratings = ratingService.getRatingsForUser(3L);

        assertEquals(2, ratings.size());
    }
}
