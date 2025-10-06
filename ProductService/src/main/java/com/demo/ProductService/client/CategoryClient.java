package com.demo.ProductService.client;

import com.demo.ProductService.DTO.CategoryResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CATEGORY-SERVICE")
public interface CategoryClient {
    @GetMapping("api/categories/name/{name}")
    CategoryResponseDTO getCategoryByName(@PathVariable("name") String name);
}
