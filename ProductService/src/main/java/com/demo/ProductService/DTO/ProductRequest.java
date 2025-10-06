package com.demo.ProductService.DTO;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private Integer stockQuantity;
    private Double price;
    private Integer lowStockThreshold;
    private String categoryName;

}