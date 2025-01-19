/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.belajar.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.belajar.ecommerce.entity.Category;

/**
 *
 * @author firman
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
