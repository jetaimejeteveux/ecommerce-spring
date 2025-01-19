/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.belajar.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.belajar.ecommerce.entity.Product;

/**
 *
 * @author firman
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
