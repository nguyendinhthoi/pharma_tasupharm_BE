package com.example.pharma_tasupharm_be.dto.product;

public interface IProductDto {
    Long getId();
    String getName();
    String getNameCategory();

    Double getPrice();

    String getImage();

    Long getIdCategory();
    Double getPriceSale();
}
