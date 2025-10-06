package com.app.common.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    // Factory methods for convenience
    public static ResourceAlreadyExistsException forCategory(String categoryName) {
        return new ResourceAlreadyExistsException("Category with name '" + categoryName + "' already exists!");
    }

    public static ResourceAlreadyExistsException forProduct(String productName) {
        return new ResourceAlreadyExistsException("Product with name '" + productName + "' already exists!");
    }

}
