package com.demo.ProductService.service.impl;


import com.demo.ProductService.DTO.CategoryResponseDTO;
import com.demo.ProductService.client.CategoryClient;
import com.demo.ProductService.repository.ProductRepository;
import com.demo.ProductService.service.ProductService;
import com.demo.ProductService.entity.Product;
import com.app.common.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private KafkaPublisherService kafkaPublisherService;

    @Override
    public Product createProduct(Product product, String categoryName) {
        Optional<Product> existingProduct = productRepository.findByName(product.getName());
        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("Product with name '" + product.getName() + "' already exists.");
        }

        product.setProductId(IdGenerator.generateSecureRandom8DigitId());

        CategoryResponseDTO categoryDTO = categoryClient.getCategoryByName(categoryName);
        product.setProductId(IdGenerator.generateSecureRandom8DigitId());

        product.setCategoryId(categoryDTO.getCategoryId());
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }


    @Override
    public Product patchProduct(Long id, Map<String, Object> updates) {
        Product product = getProductById(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    product.setName((String) value);
                    break;
                case "description":
                    product.setDescription((String) value);
                    break;
                case "price":
                    product.setPrice((Double) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field " + key + " not recognized");
            }
        });

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Override
    public Product increaseStock(Long id, Integer quantity) {
        Product product = getProductById(id);
        product.setStockQuantity(product.getStockQuantity() + quantity);
        return productRepository.save(product);
    }

    @Override
    public Product decreaseStock(Long id, Integer quantity) {
        Product updatedProduct = getProductById(id);
        if (updatedProduct.getStockQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for product " + updatedProduct.getName());
        }
        updatedProduct.setStockQuantity(updatedProduct.getStockQuantity() - quantity);

        // Publish events
        if (updatedProduct.getStockQuantity() <= updatedProduct.getLowStockThreshold()) {
            kafkaPublisherService.sendLowStockEvent(
                    "⚠️ Product " + updatedProduct.getName() + " is below threshold!"
            );
        }
        if (updatedProduct.getStockQuantity() == 0) {
            kafkaPublisherService.sendOutOfStockEvent(
                    "❌ Product " + updatedProduct.getName() + " is out of stock!"
            );
        }

        return productRepository.save(updatedProduct);
    }

    @Override
    public List<Product> getLowStockProducts() {
        return productRepository.findLowStockProducts(); // or use product.getLowStockThreshold()
    }

    @Override
    public List<Product> getLowStockProductsByCategory(Long categoryId) {
        return productRepository.findLowStockProductsByCategory(categoryId);
    }
}
