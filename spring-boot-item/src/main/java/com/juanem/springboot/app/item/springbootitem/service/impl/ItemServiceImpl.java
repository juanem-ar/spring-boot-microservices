package com.juanem.springboot.app.item.springbootitem.service.impl;

import com.juanem.springboot.app.item.springbootitem.models.dto.Item;
import com.juanem.springboot.app.item.springbootitem.models.dto.Product;
import com.juanem.springboot.app.item.springbootitem.service.IItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Primary
public class ItemServiceImpl implements IItemService {
    private final RestTemplate restClient;

    @Override
    public List<Item> getAll() {
        List<Product> products = Arrays.asList(
                restClient.getForObject("lb://service-products/api/products",Product[].class)
        );

        return products.stream().map( p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item getItemById(Long id, Integer quantity) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id",String.valueOf(id));
        pathVariables.put("quantity", String.valueOf(quantity));
        Product product = restClient.getForObject(
                "lb://service-products/api/products/{id}",
                Product.class,
                pathVariables
        );
        return new Item(product,quantity);
    }
}
