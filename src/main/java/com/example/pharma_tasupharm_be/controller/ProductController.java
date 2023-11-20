package com.example.pharma_tasupharm_be.controller;

import com.example.pharma_tasupharm_be.dto.product.DetailResponse;
import com.example.pharma_tasupharm_be.dto.product.ICategoriesDto;
import com.example.pharma_tasupharm_be.dto.product.IImageDto;
import com.example.pharma_tasupharm_be.dto.product.IProductDto;

import com.example.pharma_tasupharm_be.model.product.Product;
import com.example.pharma_tasupharm_be.service.product.IProductService;
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

    @GetMapping("/listBestSeller")
    public ResponseEntity<List<IProductDto>> getListBestSeller() {
        List<IProductDto> productDtos = productService.findAllBestSeller();
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
    public ResponseEntity<List<IProductDto>> getListNewProduct() {
        List<IProductDto> productDtos = productService.findAllNewProduct();
        if (productDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
    @GetMapping("/searchName")
    public ResponseEntity<Page<IProductDto>> searchProduct(
            @RequestParam(name ="searchName",defaultValue = "",required = false) String searchName,
            @RequestParam(name = "page",defaultValue = "0",required = false) Integer page){
        Pageable pageable = PageRequest.of(page,6);
        Page<IProductDto> productDtos = productService.findAllByName(pageable,searchName);
        if (productDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }
    @GetMapping("/detail/{idProduct}")
    public ResponseEntity<Object> getProduct(@PathVariable Long idProduct){
        Product product = productService.findProductById(idProduct);
        List<IImageDto> images = productService.findImageByProduct(idProduct);

        DetailResponse detailResponse = new DetailResponse();
        detailResponse.setProduct(product);
        detailResponse.setImages(images);
        if (product == null){
            return new ResponseEntity<>("Không tìm thấy sản phẩm",HttpStatus.NOT_FOUND);
        }
        if (images.isEmpty()){
            return new ResponseEntity<>("Không tìm thấy hình ảnh",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(detailResponse,HttpStatus.OK);
    }
    @GetMapping("/categoryByProduct/{id}")
    public ResponseEntity<Object> getProductWithCategory(@PathVariable Long id){
        List<IProductDto> productDtos = productService.findAllByCategory(id);
        if (productDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }
}
