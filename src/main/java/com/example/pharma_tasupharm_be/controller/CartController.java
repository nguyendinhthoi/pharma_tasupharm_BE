package com.example.pharma_tasupharm_be.controller;

import com.example.pharma_tasupharm_be.dto.cart.ICartDto;
import com.example.pharma_tasupharm_be.model.order.Cart;
import com.example.pharma_tasupharm_be.model.product.Product;
import com.example.pharma_tasupharm_be.model.user.AppUser;
import com.example.pharma_tasupharm_be.service.cart.ICartService;
import com.example.pharma_tasupharm_be.service.product.IProductService;
import com.example.pharma_tasupharm_be.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private ICartService cartService;
    @PostMapping("/addToCart/{idProduct}/{idUser}")
    public ResponseEntity<Object> addToCart(@PathVariable Long idProduct, @PathVariable Long idUser){
        Cart cart = new Cart();
        Product product = productService.findProductById(idProduct);
        AppUser appUser = appUserService.findUserById(idUser);
        if (product == null){
            return new ResponseEntity<>("Không tìm thấy sản phẩm", HttpStatus.NOT_FOUND);
        }
        if (appUser == null){
            return new ResponseEntity<>("Không tìm thấy tài khoản",HttpStatus.NOT_FOUND);
        }
        cart.setProduct(product);
        cart.setAppUser(appUser);
        cart.setQuantityOrder(1);
        cartService.addToCart(cart);
        return new ResponseEntity<>("Thêm vào giỏ hàng thành công",HttpStatus.OK);
    }
    @GetMapping("/getAllCart/{idUser}")
    public ResponseEntity<List<ICartDto>> getAllCart(@PathVariable Long idUser){
        List<ICartDto> cartDtos = cartService.getAllCart(idUser);
        if (cartDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(cartDtos,HttpStatus.OK);
        }
    }
}
