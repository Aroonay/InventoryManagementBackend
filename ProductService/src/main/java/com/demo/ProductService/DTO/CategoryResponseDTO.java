package com.demo.ProductService.DTO;

import lombok.Data;

@Data
public class CategoryResponseDTO {
    private Long categoryId;
    private String name;

    public CategoryResponseDTO() {
    }

    public CategoryResponseDTO(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

}
