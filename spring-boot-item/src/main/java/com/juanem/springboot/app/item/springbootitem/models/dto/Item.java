package com.juanem.springboot.app.item.springbootitem.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Product product;
    private Integer quantity;

    public Double getTotal(){
        return this.product.getPrice() * quantity.doubleValue();
    }
}
