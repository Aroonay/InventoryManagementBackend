package com.app.InventoryService.controller;

import com.app.InventoryService.DTO.ProductDTO;
import com.app.InventoryService.client.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class InventoryOperationsController {
    @Autowired
    private ProductClient productClient;


    @PutMapping("/{id}/increase")
    public ResponseEntity<ProductDTO> increaseStock(@PathVariable Long id, @RequestParam Integer amount) {
        return ResponseEntity.ok(productClient.increaseStock(id, amount));
    }

    @PutMapping("/{id}/decrease")
    public ResponseEntity<ProductDTO> decreaseStock(@PathVariable Long id, @RequestParam Integer amount) {
        return ResponseEntity.ok(productClient.decreaseStock(id, amount));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductDTO>> getLowStockProducts() {
        return ResponseEntity.ok(productClient.getLowStockProducts());
    }

    @GetMapping("/low-stock/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getLowStockProductsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productClient.getLowStockProductsByCategory(categoryId));
    }

}
