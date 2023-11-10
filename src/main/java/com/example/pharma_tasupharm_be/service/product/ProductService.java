package com.example.pharma_tasupharm_be.service.product;

import com.example.pharma_tasupharm_be.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository productRepository;
}
