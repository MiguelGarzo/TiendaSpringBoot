package com.tiendaonline.tienda.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Class to throw the 404 error.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id){
        super("Product with id: " + id + " not found.");
    }
}