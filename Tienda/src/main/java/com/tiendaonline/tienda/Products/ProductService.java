package com.tiendaonline.tienda.products;

import org.springframework.stereotype.Service;
import java.util.List;

// Making this class a Service (Just logic class, no HTTP, no SQL)
@Service
public class ProductService {
    // Creating the variable relation with our repository class
    private final ProductRepository repository;

    // Conecting our classes
    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    // Method to list all our products
    public List<Product> getAll() {
        return repository.findAll();
    }

    // Method to save a product in our DB
    public Product save(Product product){
        return repository.save(product);
    }

    // Function to find a product by it's id, if there's no product found it send a Error Message.
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    // Method to update a product by it's id
    public Product update(Long id, Product uProduct){
        // Asking for a Product by it's id, it will be saved at the variable cProduct to update it.
        Product cProduct = findById(id);

        cProduct.setName(uProduct.getName());
        cProduct.setPrice(uProduct.getPrice());
        cProduct.setStock(uProduct.getStock());

        return repository.save(cProduct);
    }
}