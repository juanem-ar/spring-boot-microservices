package com.juanem.springboot.app.item.springbootitem.service;

import com.juanem.springboot.app.item.springbootitem.models.dto.Item;

import java.util.List;

public interface IItemService {
    List<Item> getAll();
    Item getItemById(Long id, Integer quantity);
}
