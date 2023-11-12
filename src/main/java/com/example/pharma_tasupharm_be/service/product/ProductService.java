package com.example.pharma_tasupharm_be.service.product;

import com.example.pharma_tasupharm_be.dto.product.ICategoriesDto;
import com.example.pharma_tasupharm_be.dto.product.ProductDto;
import com.example.pharma_tasupharm_be.repository.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Page<ProductDto> findAllProduct(Pageable pageable, String name) {
        return productRepository.findAllProduct(pageable, "%" + name + "%");
    }

    @Override
    public List<ProductDto> findAllBestSeller() {
        return productRepository.findAllBestSeller();
    }

    @Override
    public List<ICategoriesDto> findAllCategories() {
        return productRepository.findAllCategories();
    }

    @Override
    public List<ProductDto> findAllNewProduct() {
        return productRepository.findAllNewProduct();
    }
}
