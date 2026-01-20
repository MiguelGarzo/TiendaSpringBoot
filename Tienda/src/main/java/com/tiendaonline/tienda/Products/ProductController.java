package com.tiendaonline.tienda.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
// Makes the endpoint to /products
@RequestMapping("/products")
public class ProductController {
    // Creates the varialbe who will contain a ProductService instance
    private final ProductService service;

    // Makes the relation beetween Controller and Service
    public ProductController(ProductService service) {

        this.service = service;
    }

    // Maps the GET method to list products(I tried with PostMan)
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAll());
    }

    // Maps the GET method to list products by the required id (I tried with PostMan)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    // Maps the POST method to create products (I tried with PostMan)
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    
    // Maps the PUT method to update products (I tried with PostMan)
    // You have to insert your URL with the /id of the product you want to update, then make a RAW file with the updates.
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        return service.update(id, product);
    }

    // Mapping DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}