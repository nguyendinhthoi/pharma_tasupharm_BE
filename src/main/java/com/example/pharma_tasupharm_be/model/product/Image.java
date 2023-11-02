package com.example.pharma_tasupharm_be.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "LONGTEXT")
    private String name;
    @ManyToOne
    @JoinColumn(name = "idProduct",referencedColumnName = "id")
    private Product product;
}
