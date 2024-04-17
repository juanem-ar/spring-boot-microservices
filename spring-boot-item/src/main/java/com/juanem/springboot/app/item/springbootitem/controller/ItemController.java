package com.juanem.springboot.app.item.springbootitem.controller;

import com.juanem.springboot.app.item.springbootitem.models.dto.Item;
import com.juanem.springboot.app.item.springbootitem.service.IItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final IItemService iItemService;
    private final CircuitBreakerFactory circuitBreakerFactory;
    @Value("${configuration.text}")
    private String texto;

    @GetMapping("/get-config")
    public ResponseEntity<Map<String, String>> getConfig(@Value("${server.port}") String port){
        log.info("TEXTO: {}",texto + "PORT: {}", port );
        Map<String, String> json = new HashMap<>();
        json.put("texto", texto);
        json.put("port", port);
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }
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
