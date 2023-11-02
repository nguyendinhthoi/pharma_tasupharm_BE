package com.example.pharma_tasupharm_be.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private String description;
    @ManyToOne
    @JoinColumn(name = "idCategory", referencedColumnName = "id")
    private Category category;
}
