package com.juanem.springboot.app.item.springbootitem.clients;

import com.juanem.springboot.app.item.springbootitem.models.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-products", url = "http://localhost:8080/api/products")
public interface ProductClientRest {
    @GetMapping()
    public List<Product> gelAllProducts();

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id);
}
