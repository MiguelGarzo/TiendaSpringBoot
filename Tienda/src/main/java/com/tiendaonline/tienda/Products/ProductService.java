package com.tiendaonline.tienda.products;

import com.tiendaonline.tienda.products.dto.ProductRequestDTO;
import com.tiendaonline.tienda.products.dto.ProductResponseDTO;
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

    // Method to save a DTO as a product in our DB and return a DTO to the client/user
    public ProductResponseDTO save(ProductRequestDTO pRequested){
        Product product = new Product();

        product.setName(pRequested.getName());
        product.setPrice(pRequested.getPrice());
        product.setStock(pRequested.getStock());

        Product newProduct = repository.save(product);

        return new ProductResponseDTO(
                newProduct.getId(),
                newProduct.getName(),
                newProduct.getPrice(),
                newProduct.getStock()
        );
    }

    // Function to find a product by its id, if there's no product found it send a Error Message.
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    // Method to update a product by its id
    public Product update(Long id, Product uProduct){
        // Asking for a Product by its id, it will be saved at the variable cProduct to update it.
        Product cProduct = findById(id);

        cProduct.setName(uProduct.getName());
        cProduct.setPrice(uProduct.getPrice());
        cProduct.setStock(uProduct.getStock());

        return repository.save(cProduct);
    }

    // Method to delete a product by its id
    public void delete(Long id){
        Product cProduct = findById(id);
        repository.delete(cProduct);
    }

    private ProductResponseDTO toRespons(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        return dto;
    }
}