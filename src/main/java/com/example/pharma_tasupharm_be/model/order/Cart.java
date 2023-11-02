package com.example.pharma_tasupharm_be.model.order;

import com.example.pharma_tasupharm_be.model.product.Product;
import com.example.pharma_tasupharm_be.model.user.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantityOrder;
    @ManyToOne
    @JoinColumn(name = "idProduct", referencedColumnName = "id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private AppUser appUser;
}
