package com.tiendaonline.tienda.products;

import org.springframework.data.jpa.repository.JpaRepository;

// Makes the connectivity with the DB
public interface ProductRepository extends JpaRepository<Product, Long> {
}