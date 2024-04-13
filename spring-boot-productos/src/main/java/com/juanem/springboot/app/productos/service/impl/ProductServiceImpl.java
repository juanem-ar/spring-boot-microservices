package com.juanem.springboot.app.productos.service.impl;

import com.juanem.springboot.app.productos.models.dto.ProductRequestDto;
import com.juanem.springboot.app.productos.models.entity.ProductEntity;
import com.juanem.springboot.app.productos.repository.IProductRepository;
import com.juanem.springboot.app.productos.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository iProductRepository;

    @Override
    public String saveProduct(ProductRequestDto dto) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductEntity getProductById(Long id) {
        return iProductRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductEntity> getAllProduct() {
        return iProductRepository.findAll();
    }
}
