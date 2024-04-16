package com.juanem.springboot.app.item.springbootitem.controller;

import com.juanem.springboot.app.item.springbootitem.models.dto.Item;
import com.juanem.springboot.app.item.springbootitem.models.dto.Product;
import com.juanem.springboot.app.item.springbootitem.service.IItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {
    private final IItemService iItemService;
    private final CircuitBreakerFactory circuitBreakerFactory;
    @CircuitBreaker(name = "service-items", fallbackMethod = "getItemsFallback")
    @GetMapping("/listar")
    public List<Item> getAllItems(@RequestParam(name="nameParam", required = false) String name, @RequestHeader(name = "token-request", required = false) String token){
        System.out.println("NOMBRE: " + name + ", TOKEN: " + token);
        return circuitBreakerFactory.create("items")
                .run(iItemService::getAll);
    }
    @GetMapping("/{id}/{quantity}")
    public Item getItemById(@PathVariable Long id, @PathVariable Integer quantity){
        return circuitBreakerFactory.create("item")
                .run(()->iItemService.getItemById(id, quantity));
    }
    public ResponseEntity<String> getItemsFallback(Throwable throwable){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(throwable.getMessage());
    }
}
