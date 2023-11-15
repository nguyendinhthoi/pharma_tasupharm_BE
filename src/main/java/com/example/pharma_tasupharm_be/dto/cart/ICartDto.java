package com.example.pharma_tasupharm_be.dto.cart;

public interface ICartDto {
    Long getIdUser();
    Long getIdProduct();
    String getName();
    Double getPrice();
    Double getPriceSale();
    String getImage();
    Integer getQuantity();

}
