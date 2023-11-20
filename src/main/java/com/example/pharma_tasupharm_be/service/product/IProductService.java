package com.example.pharma_tasupharm_be.service.product;

import com.example.pharma_tasupharm_be.dto.product.ICategoriesDto;
import com.example.pharma_tasupharm_be.dto.product.IImageDto;
import com.example.pharma_tasupharm_be.dto.product.IProductDto;
import com.example.pharma_tasupharm_be.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService{
    Page<IProductDto> findAllProduct(Pageable pageable, String name);

    List<IProductDto> findAllBestSeller();

    List<ICategoriesDto> findAllCategories();

    List<IProductDto> findAllNewProduct();

    Page<IProductDto> findAllByName(Pageable pageable, String searchName);

    Product findProductById(Long idProduct);

    List<IImageDto> findImageByProduct(Long idProduct);

    List<IProductDto> findAllByCategory(Long id);
}
