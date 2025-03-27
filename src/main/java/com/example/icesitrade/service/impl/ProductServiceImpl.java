package com.example.icesitrade.service.impl;

import com.example.icesitrade.model.Product;
import com.example.icesitrade.repository.ProductRepository;
import com.example.icesitrade.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(existing -> {
            existing.setTitle(updatedProduct.getTitle());
            existing.setDescription(updatedProduct.getDescription());
            existing.setPrice(updatedProduct.getPrice());
            existing.setStatus(updatedProduct.getStatus());
            existing.setLocation(updatedProduct.getLocation());
            existing.setImageUrl(updatedProduct.getImageUrl());
            existing.setCategory(updatedProduct.getCategory());
            existing.setSeller(updatedProduct.getSeller());
            return productRepository.save(existing);
        }).orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
