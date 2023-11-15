package com.example.pharma_tasupharm_be.controller;

import com.example.pharma_tasupharm_be.dto.product.ICategoriesDto;
import com.example.pharma_tasupharm_be.dto.product.ProductDto;
import com.example.pharma_tasupharm_be.model.order.Cart;
import com.example.pharma_tasupharm_be.model.product.Product;
import com.example.pharma_tasupharm_be.model.user.AppUser;
import com.example.pharma_tasupharm_be.service.product.IProductService;
import com.example.pharma_tasupharm_be.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IAppUserService appUserService;

    @GetMapping("/listBestSeller")
    public ResponseEntity<List<ProductDto>> getListBestSeller() {
        List<ProductDto> productDtos = productService.findAllBestSeller();
        if (productDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ICategoriesDto>> getListCategories() {
        List<ICategoriesDto> categories = productService.findAllCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/listNewProduct")
    public ResponseEntity<List<ProductDto>> getListNewProduct() {
        List<ProductDto> productDtos = productService.findAllNewProduct();
        if (productDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
    @GetMapping("/searchName")
    public ResponseEntity<Page<ProductDto>> searchProduct(
            @RequestParam(name ="searchName",defaultValue = "",required = false) String searchName,
            @RequestParam(name = "page",defaultValue = "0",required = false) Integer page){
        Pageable pageable = PageRequest.of(page,6);
        Page<ProductDto> productDtos = productService.findAllByName(pageable,searchName);
        if (productDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }

}
