package com.example.pharma_tasupharm_be.service.cart;

import com.example.pharma_tasupharm_be.dto.cart.ICartDto;
import com.example.pharma_tasupharm_be.model.order.Cart;

import java.util.List;

public interface ICartService {
    void addToCart(Cart cart);

    List<ICartDto> getAllCart(Long idUser);

    Integer deleteProduct(Long idUser, Long idProduct);
}
