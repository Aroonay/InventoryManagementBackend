package com.demo.CategoryService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CategoryResponseDTO {
    private Long categoryId;
    private String name;

    public CategoryResponseDTO(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
}
