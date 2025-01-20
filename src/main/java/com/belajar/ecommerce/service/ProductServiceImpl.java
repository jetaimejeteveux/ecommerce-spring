/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.ecommerce.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.belajar.ecommerce.common.errors.ResourceNotFoundException;
import com.belajar.ecommerce.entity.Category;
import com.belajar.ecommerce.entity.Product;
import com.belajar.ecommerce.entity.ProductCategory;
import com.belajar.ecommerce.entity.ProductCategory.ProductCategoryId;
import com.belajar.ecommerce.model.CategoryResponse;
import com.belajar.ecommerce.model.ProductRequest;
import com.belajar.ecommerce.model.ProductResponse;
import com.belajar.ecommerce.repository.CategoryRepository;
import com.belajar.ecommerce.repository.ProductCategoryRepository;
import com.belajar.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author firman
 */

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductResponse> findAll() {
      return productRepository.findAll().stream().map(product -> {
        List<CategoryResponse> productCategories = getProductCategories(product.getProductId());
        return ProductResponse.fromProductAndCategories(product, productCategories);
      }).toList();
    }

    @Override
    public ProductResponse findByid(Long productId) {
        Product existingProduct = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
        List<CategoryResponse> productCategories = getProductCategories(productId);
        return ProductResponse.fromProductAndCategories(existingProduct, productCategories);
    }

    @Override
    public ProductResponse create(ProductRequest productRequest) {
        Product product = Product.builder()
        .name(productRequest.getName())
        .description(productRequest.getDescription())
        .price(productRequest.getPrice())
        .stockQuantity(productRequest.getStockQuantity())
        .weight(productRequest.getWeight())
        .build();
        Product savedProduct = productRepository.save(product);

        List<Category> categories = getCategoriesByIds(productRequest.getCategoryIds());
        List<ProductCategory> productCategories = categories.stream().map(category -> {
            ProductCategoryId productCategoryId = new ProductCategoryId();
            productCategoryId.setCategoryId(category.getCategoryId());
            productCategoryId.setProductId(savedProduct.getProductId());

            return ProductCategory.builder().id(productCategoryId).build();
        }).toList();
        productCategoryRepository.saveAll(productCategories);

        List<CategoryResponse> categoryResponselist = categories.stream().map(
            CategoryResponse::fromCategory
        ).toList();

        return ProductResponse.fromProductAndCategories(savedProduct, categoryResponselist);
        
    }

    @Override
    public ProductResponse update(ProductRequest productRequest, Long productId) {
        Product existingProduct = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("product not found with id = " + productId));

        existingProduct.setProductId(productId);
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setStockQuantity(productRequest.getStockQuantity());
        existingProduct.setWeight(productRequest.getWeight());
        productRepository.save(existingProduct);

        List<Category> categories = getCategoriesByIds(productRequest.getCategoryIds());
        List<ProductCategory> existingProductCategories = productCategoryRepository.findCategoriesByProductId(productId);
        productCategoryRepository.deleteAll(existingProductCategories);

        List<ProductCategory> productCategories = categories.stream().map(category -> {
            ProductCategoryId productCategoryId = new ProductCategoryId();
            productCategoryId.setCategoryId(category.getCategoryId());
            productCategoryId.setProductId(productId);
            return ProductCategory.builder().id(productCategoryId).build();
        }).toList();
        productCategoryRepository.saveAll(productCategories);

        List<CategoryResponse> categoryResponses = categories.stream()
        .map(CategoryResponse::fromCategory)
        .toList();

        return ProductResponse.fromProductAndCategories(existingProduct, categoryResponses);
    }

    @Override
    public void delete(Long productId) {
        Product existingProduct = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
        
        List<ProductCategory> productCategories = productCategoryRepository.findCategoriesByProductId(productId);

        productCategoryRepository.deleteAll(productCategories);
        productRepository.delete(existingProduct);
    }

    private List<Category> getCategoriesByIds(List<Long> categoryIds) {
        return categoryIds.stream().map(
            categoryId -> categoryRepository.findById(categoryId)
               .orElseThrow(() -> new ResourceNotFoundException("Category not found for id :" + categoryId)))
               .toList();
    }

    private List<CategoryResponse> getProductCategories(Long productId) {
        List<ProductCategory> productCategories = productCategoryRepository.findCategoriesByProductId(productId);
        
        List<Long> categoryIds = productCategories.stream()
        .map(productCategory -> productCategory.getId().getCategoryId())
        .toList();

        return categoryRepository.findAllById(categoryIds).stream()
        .map(CategoryResponse::fromCategory)
        .toList();
    }
}
