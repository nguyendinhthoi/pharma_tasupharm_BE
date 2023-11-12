package com.example.pharma_tasupharm_be.dto.product;

public interface ProductDto {
    Long getId();
    String getName();

    Double getPrice();

    String getImage();

    Long getIdCategory();
}
