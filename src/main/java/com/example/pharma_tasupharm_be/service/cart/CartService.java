package com.example.pharma_tasupharm_be.service.cart;

import com.example.pharma_tasupharm_be.dto.cart.ICartDto;
import com.example.pharma_tasupharm_be.model.order.Cart;
import com.example.pharma_tasupharm_be.repository.cart.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService{
    @Autowired
    private ICartRepository cartRepository;
    @Override
    public void addToCart(Cart cart) {
        cartRepository.addToCart(cart);
    }

    @Override
    public List<ICartDto> getAllCart(Long idUser) {
        return cartRepository.getAllCart(idUser);
    }

    @Override
    public Integer deleteProduct(Long idUser, Long idProduct) {
        Integer integer = cartRepository.deleteProduct(idUser,idProduct);
        return integer;
    }
}
