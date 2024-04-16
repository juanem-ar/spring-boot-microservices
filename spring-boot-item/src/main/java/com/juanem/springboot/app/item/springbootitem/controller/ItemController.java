package com.juanem.springboot.app.item.springbootitem.controller;

import com.juanem.springboot.app.item.springbootitem.models.dto.Item;
import com.juanem.springboot.app.item.springbootitem.service.IItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    private final IItemService iItemService;

    @GetMapping("/listar")
    public List<Item> getAllItems(@RequestParam(name="nameParam", required = false) String name, @RequestHeader(name = "token-request", required = false) String token){
        System.out.println("NOMBRE: " + name + ", TOKEN: " + token);
        return iItemService.getAll();
    }
    @GetMapping("/{id}/{quantity}")
    public Item getItemById(@PathVariable Long id, @PathVariable Integer quantity){
        return iItemService.getItemById(id, quantity);
    }
}
