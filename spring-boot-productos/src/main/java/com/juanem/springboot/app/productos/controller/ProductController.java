package com.juanem.springboot.app.productos.controller;

import com.juanem.springboot.app.productos.models.dto.ProductRequestDto;
import com.juanem.springboot.app.productos.models.entity.ProductEntity;
import com.juanem.springboot.app.productos.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;

    @PostMapping()
    public String saveProduct(@RequestBody ProductRequestDto dto){
        return iProductService.saveProduct(dto);
    }

    @GetMapping("/{id}")
    public ProductEntity getProductById(@PathVariable Long id){
        return iProductService.getProductById(id);
    }

    @GetMapping()
    public List<ProductEntity> getAllProduct(){
        return iProductService.getAllProduct();
    }
}
