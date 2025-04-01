package com.example.icesitrade.service;

import com.example.icesitrade.model.Category;
import com.example.icesitrade.model.Product;
import com.example.icesitrade.model.User;
import com.example.icesitrade.repository.ProductRepository;
import com.example.icesitrade.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void testCreateProduct() {
        Product product = Product.builder()
                .title("Test Product")
                .description("Description")
                .price(100.0)
                .status("Available")
                .location("Cali")
                .imageUrl("url.com")
                .category(new Category())
                .build();

        product.setSeller(new User());


        when(productRepository.save(product)).thenReturn(product);

        Product saved = productService.createProduct(product);

        assertNotNull(saved);
        assertEquals("Test Product", saved.getTitle());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> found = productService.getProductById(1L);

        assertTrue(found.isPresent());
        assertEquals(1L, found.get().getId());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteProduct() {
        Long id = 1L;

        doNothing().when(productRepository).deleteById(id);

        productService.deleteProduct(id);

        verify(productRepository, times(1)).deleteById(id);
    }
}
