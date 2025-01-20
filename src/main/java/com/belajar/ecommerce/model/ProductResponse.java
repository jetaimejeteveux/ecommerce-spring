/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.belajar.ecommerce.entity.Product;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author firman
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class ProductResponse {
private Long productId;
private String name;
private BigDecimal price;
private String description;
private Integer stockQuantity;
private BigDecimal weight;
private LocalDateTime createdAt;
private LocalDateTime updatedAt;
private List<CategoryResponse> categories;

public static ProductResponse fromProductAndCategories(Product product, List<CategoryResponse> categories) {
    return ProductResponse.builder()
        .productId(product.getProductId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .stockQuantity(product.getStockQuantity())
        .weight(product.getWeight())
        .createdAt(product.getCreatedAt())
        .updatedAt(product.getUpdatedAt())
        .categories(categories)
        .build();
    }
}
