package com.tiendaonline.tienda.products;

import jakarta.persistence.*;
import lombok.*;

// Make a table in PostgreSQL
@Entity

// Make the Getters
@Getter

// Make the Setters
@Setter

// Make a empty constructor needed for JPA
@NoArgsConstructor

//Make a full constructor with all the attributes
@AllArgsConstructor
public class Product {
    
    // Define the PrimaryKey as autogenerate in PostgreSQL
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Integer stock;

}
