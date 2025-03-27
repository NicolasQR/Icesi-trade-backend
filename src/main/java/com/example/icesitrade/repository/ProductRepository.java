package com.example.icesitrade.repository;

import com.example.icesitrade.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
