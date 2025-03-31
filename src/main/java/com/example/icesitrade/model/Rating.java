package com.example.icesitrade.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score; // de 1 a 5 estrellas

    private String comment;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User reviewer; // quien califica

    @ManyToOne
    @JoinColumn(name = "reviewed_id")
    private User reviewed; // a quién califican
}
