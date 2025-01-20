/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.belajar.ecommerce.service;

import java.util.List;
import com.belajar.ecommerce.model.ProductRequest;
import com.belajar.ecommerce.model.ProductResponse;

/**
 *
 * @author firman
 */

public interface ProductService {
    List<ProductResponse> findAll();

    ProductResponse findByid(Long id);
    
    ProductResponse create(ProductRequest productRequest);
    
    ProductResponse update(ProductRequest productRequest, Long productId);
    
    void delete(Long id);
}
