package com.demo.ProductService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private Long productId;

    private String name;

    private String description;

    private Integer stockQuantity;

    private Double price;

    private Integer lowStockThreshold;

    private Long categoryId;
}

