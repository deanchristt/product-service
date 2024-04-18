package com.technologies.productservice.model.dto;

import com.technologies.productservice.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private String name;

    private Product.ProductType type;

    private double price;

    private int quantity;
}
