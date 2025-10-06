package com.demo.ProductService.controller;

import com.demo.ProductService.DTO.ProductRequest;
import com.demo.ProductService.entity.Product;
import com.demo.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setStockQuantity(request.getStockQuantity());
        product.setPrice(request.getPrice());
        product.setLowStockThreshold(request.getLowStockThreshold());

        Product savedProduct = productService.createProduct(product, request.getCategoryName());
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return new ResponseEntity<>(productService.patchProduct(id, updates), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



    //InventoryOperations

    @PutMapping("/{id}/increase-stock")
    public ResponseEntity<Product> increaseStock(@PathVariable Long id, @RequestParam Integer amount) {
        return ResponseEntity.ok(productService.increaseStock(id, amount));
    }

    @PutMapping("/{id}/decrease-stock")
    public ResponseEntity<Product> decreaseStock(@PathVariable Long id, @RequestParam Integer amount) {
        return ResponseEntity.ok(productService.decreaseStock(id, amount));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<Product>> getLowStockProducts() {
        return ResponseEntity.ok(productService.getLowStockProducts());
    }

    @GetMapping("/low-stock/{categoryId}")
    public ResponseEntity<List<Product>> getLowStockProductsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getLowStockProductsByCategory(categoryId));
    }

}
