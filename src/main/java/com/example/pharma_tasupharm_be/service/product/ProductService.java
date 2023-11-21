package com.example.pharma_tasupharm_be.service.product;

import com.example.pharma_tasupharm_be.dto.product.ICategoriesDto;
import com.example.pharma_tasupharm_be.dto.product.IImageDto;
import com.example.pharma_tasupharm_be.dto.product.IProductDetail;
import com.example.pharma_tasupharm_be.dto.product.IProductDto;
import com.example.pharma_tasupharm_be.model.product.Product;
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
    public Page<IProductDto> findAllProduct(Pageable pageable, String name) {
        return productRepository.findAllProduct(pageable, "%" + name + "%");
    }

    @Override
    public List<IProductDto> findAllBestSeller() {
        return productRepository.findAllBestSeller();
    }

    @Override
    public List<ICategoriesDto> findAllCategories() {
        return productRepository.findAllCategories();
    }

    @Override
    public List<IProductDto> findAllNewProduct() {
        return productRepository.findAllNewProduct();
    }

    @Override
    public Page<IProductDto> findAllByName(Pageable pageable, String searchName) {
        return productRepository.findAllByName(pageable,"%"+searchName+"%");
    }

    @Override
    public Product findProductById(Long idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    @Override
    public List<IImageDto> findImageByProduct(Long idProduct) {
        return productRepository.findImageByProduct(idProduct);
    }

    @Override
    public List<IProductDto> findAllByCategory(Long id) {
        return productRepository.findAllByCategory(id);
    }

    @Override
    public Product findProductInCart(Long idProduct) {
        return productRepository.findProductInCart(idProduct);
    }

    @Override
    public IProductDetail findProductDtoById(Long idProduct) {
        return productRepository.findProductDtoById(idProduct);
    }

    @Override
    public Page<IProductDto> findPageByCategory(Pageable pageable, Long idCategory) {
        return productRepository.findProductByCategory(pageable,idCategory);
    }
}
