package com.juanem.springboot.app.productos.controller;

import com.juanem.springboot.app.productos.models.dto.ProductRequestDto;
import com.juanem.springboot.app.productos.models.entity.ProductEntity;
import com.juanem.springboot.app.productos.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService iProductService;
    private final Environment env;



    @PostMapping()
    public String saveProduct(@RequestBody ProductRequestDto dto){
        return iProductService.saveProduct(dto);
    }

    @GetMapping("/{id}")
    public ProductEntity getProductById(@PathVariable Long id){
        var product = iProductService.getProductById(id);
        product.setPort(env.getProperty("local.server.port"));
        return product;
    }

    @GetMapping()
    public List<ProductEntity> getAllProduct(){
        return iProductService.getAllProduct().stream().map(p -> {
            p.setPort(env.getProperty("local.server.port"));
            return p;
        }).collect(Collectors.toList());
    }
}
