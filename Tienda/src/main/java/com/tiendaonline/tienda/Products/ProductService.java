package com.tiendaonline.tienda.products;

import org.springframework.stereotype.Services;
import java.util.List;

@Services
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product save(Product product){
        return repository.save(product);
    }
}