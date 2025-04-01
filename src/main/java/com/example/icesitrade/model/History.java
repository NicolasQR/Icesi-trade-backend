package com.example.icesitrade.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String type; // "COMPRA" o "VENTA"

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime timestamp;
}
