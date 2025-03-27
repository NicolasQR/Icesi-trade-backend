package com.example.icesitrade.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private double price;

    private String status; // e.g., "Available", "Sold"

    private String location;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
