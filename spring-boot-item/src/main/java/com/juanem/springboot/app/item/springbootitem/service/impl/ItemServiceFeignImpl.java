package com.juanem.springboot.app.item.springbootitem.service.impl;

import com.juanem.springboot.app.item.springbootitem.clients.ProductClientRest;
import com.juanem.springboot.app.item.springbootitem.models.dto.Item;
import com.juanem.springboot.app.item.springbootitem.service.IItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
public class ItemServiceFeignImpl implements IItemService {

    private final ProductClientRest clientFeign;

    @Override
    public List<Item> getAll() {
        return clientFeign.gelAllProducts().stream().map( p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item getItemById(Long id, Integer quantity) {
        return new Item(clientFeign.getProductById(id), quantity);
    }
}
