package com.example.pharma_tasupharm_be.controller;

import com.example.pharma_tasupharm_be.dto.ProductDto;
import com.example.pharma_tasupharm_be.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public ResponseEntity<Page<ProductDto>> getList(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "searchName", defaultValue = "", required = false) String name) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<ProductDto> productDtos = productService.findAllProduct(pageable,);
    }
}
