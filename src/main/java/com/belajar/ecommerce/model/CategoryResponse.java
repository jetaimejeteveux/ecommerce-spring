/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.ecommerce.model;

import com.belajar.ecommerce.entity.Category;
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
public class CategoryResponse {
  private Long categoryId;
  private String name;

  public static CategoryResponse fromCategory(Category category) {
    return CategoryResponse.builder()
        .categoryId(category.getCategoryId())
        .name(category.getName())
        .build();
  }
}
