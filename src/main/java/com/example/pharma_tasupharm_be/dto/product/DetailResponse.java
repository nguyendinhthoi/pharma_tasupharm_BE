package com.example.pharma_tasupharm_be.dto.product;

import com.example.pharma_tasupharm_be.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailResponse {
    private IProductDetail product;
    private List<IImageDto> images;
}
