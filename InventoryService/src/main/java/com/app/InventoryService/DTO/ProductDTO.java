package com.app.InventoryService.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private Integer stockQuantity;
    private Double price;
    private Integer lowStockThreshold;
    private Long categoryId;
}
