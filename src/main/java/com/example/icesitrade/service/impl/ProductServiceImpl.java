package com.example.icesitrade.service.impl;

import com.example.icesitrade.model.History;
import com.example.icesitrade.model.Product;
import com.example.icesitrade.repository.ProductRepository;
import com.example.icesitrade.service.HistoryService;
import com.example.icesitrade.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final HistoryService historyService;


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
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Guardar el estado anterior para comparar
        String previousStatus = existingProduct.getStatus();

        existingProduct.setTitle(updatedProduct.getTitle());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setLocation(updatedProduct.getLocation());
        existingProduct.setImageUrl(updatedProduct.getImageUrl());
        existingProduct.setStatus(updatedProduct.getStatus());

        Product savedProduct = productRepository.save(existingProduct);


        if (!"Vendido".equalsIgnoreCase(previousStatus) &&
                "Vendido".equalsIgnoreCase(updatedProduct.getStatus())) {

            History history = History.builder()
                    .user(existingProduct.getSeller())
                    .product(existingProduct)
                    .type("VENTA")
                    .timestamp(LocalDateTime.now())
                    .build();

            historyService.saveHistory(history);
        }

        return savedProduct;
    }


    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
