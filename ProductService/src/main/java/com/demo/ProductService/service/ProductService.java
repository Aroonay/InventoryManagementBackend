package com.demo.ProductService.service;

import com.demo.ProductService.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product createProduct(Product product, String categoryName);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product updateProduct(Long id, Product productDetails);

    Product patchProduct(Long id, Map<String, Object> updates);

    void deleteProduct(Long id);

    // Stock management
    Product increaseStock(Long id, Integer quantity);

    Product decreaseStock(Long id, Integer quantity);

    List<Product> getLowStockProducts();

    List<Product> getLowStockProductsByCategory(Long categoryId);
}
