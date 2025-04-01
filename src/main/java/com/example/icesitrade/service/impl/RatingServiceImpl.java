package com.example.icesitrade.service.impl;

import com.example.icesitrade.model.Notification;
import com.example.icesitrade.model.Rating;
import com.example.icesitrade.model.User;
import com.example.icesitrade.repository.RatingRepository;
import com.example.icesitrade.repository.UserRepository;
import com.example.icesitrade.service.NotificationService;
import com.example.icesitrade.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final NotificationService notificationService;
    private final UserRepository userRepository;

    @Override
    public Rating createRating(Rating rating) {
        if (rating.getReviewer().getId().equals(rating.getReviewed().getId())) {
            throw new IllegalArgumentException("No puedes calificarte a ti mismo.");
        }

        rating.setTimestamp(LocalDateTime.now());
        Rating savedRating = ratingRepository.save(rating);

        // Cargar el usuario completo desde DB para obtener su nombre
        User reviewer = userRepository.findById(rating.getReviewer().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Crear notificación para el usuario calificado
        Notification notification = Notification.builder()
                .user(rating.getReviewed())
                .content("Has recibido una nueva calificación de " + reviewer.getName())
                .timestamp(LocalDateTime.now())
                .read(false)
                .build();

        notificationService.createNotification(notification);

        return savedRating;
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
