package com.example.pharma_tasupharm_be.service.product;

import com.example.pharma_tasupharm_be.dto.product.ICategoriesDto;
import com.example.pharma_tasupharm_be.dto.product.ProductDto;
import com.example.pharma_tasupharm_be.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService{
    Page<ProductDto> findAllProduct(Pageable pageable, String name);

    List<ProductDto> findAllBestSeller();

    List<ICategoriesDto> findAllCategories();

    List<ProductDto> findAllNewProduct();

    Page<ProductDto> findAllByName(Pageable pageable, String searchName);

    Product findProductById(Long idProduct);
}
