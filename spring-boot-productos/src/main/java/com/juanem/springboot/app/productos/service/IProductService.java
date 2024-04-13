package com.juanem.springboot.app.productos.service;

import com.juanem.springboot.app.productos.models.dto.ProductRequestDto;
import com.juanem.springboot.app.productos.models.entity.ProductEntity;

import java.util.List;

public interface IProductService {

    String saveProduct(ProductRequestDto dto);

    ProductEntity getProductById(Long id);

    List<ProductEntity> getAllProduct();
}
