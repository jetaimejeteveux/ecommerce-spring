/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.belajar.ecommerce.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.belajar.ecommerce.entity.ProductCategory;
import com.belajar.ecommerce.entity.ProductCategory.ProductCategoryId;

/**
 *
 * @author firman
 */

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, ProductCategoryId> {
    @Query(value = """
    SELECT * FROM product_category
    WHERE product_id = :productId
    """, nativeQuery = true)
    List<ProductCategory> findCategoriesByProductId(@Param("productId") Long productId);
}
