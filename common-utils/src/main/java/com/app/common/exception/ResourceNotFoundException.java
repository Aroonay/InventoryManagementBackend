package com.app.common.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public static ResourceNotFoundException forCategory(String categoryId) {
        return new ResourceNotFoundException("Category not found with id: " + categoryId);
    }

    public static ResourceNotFoundException forProduct(String productId) {
        return new ResourceNotFoundException("Product not found with id: " + productId);
    }
}
