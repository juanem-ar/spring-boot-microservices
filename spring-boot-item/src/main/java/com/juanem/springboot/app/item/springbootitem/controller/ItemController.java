package com.juanem.springboot.app.item.springbootitem.controller;

import com.juanem.springboot.app.item.springbootitem.models.dto.Item;
import com.juanem.springboot.app.item.springbootitem.service.IItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    private final IItemService iItemService;

    @GetMapping("/listar")
    public List<Item> getAllItems(){
        return iItemService.getAll();
    }
    @GetMapping("/{id}/{quantity}")
    public Item getItemById(@PathVariable Long id, @PathVariable Integer quantity){
        return iItemService.getItemById(id, quantity);
    }
}
