package com.app.InventoryService.client;


import com.app.InventoryService.DTO.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {

    @PutMapping("/api/products/{id}/increase-stock")
    ProductDTO increaseStock(@PathVariable("id") Long productId, @RequestParam("amount") Integer amount);

    @PutMapping("/api/products/{id}/decrease-stock")
    ProductDTO decreaseStock(@PathVariable("id") Long productId, @RequestParam("amount") Integer amount);

    @GetMapping("/api/products/low-stock")
    List<ProductDTO> getLowStockProducts();

    @GetMapping("/api/products/low-stock/{categoryId}")
    List<ProductDTO> getLowStockProductsByCategory(@PathVariable("categoryId") Long categoryId);

}

