package com.belajar.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.belajar.ecommerce.model.ErrorResponse;
import com.belajar.ecommerce.model.ProductRequest;
import com.belajar.ecommerce.model.ProductResponse;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



@RestController
@RequestMapping("products")
public class ProductController {

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
            ProductResponse.builder()
            .name("product" + id)
            .description("product description")
            .price(BigDecimal.ONE)
            .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(
            List.of(
                ProductResponse.builder()
                .name("product 1")
                .price(BigDecimal.ONE)
                .description("product 1 description")
                .build(),
                ProductResponse.builder()
                .name("product 2")
                .price(BigDecimal.TEN)
                .description("product 2 description")
                .build()
            ));
    }

    @PostMapping("")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
         return ResponseEntity.ok(
            ProductResponse.builder()
            .name(productRequest.getName())
            .description(productRequest.getDescription())
            .price(BigDecimal.ONE)
            .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
        @RequestBody @Valid ProductRequest productRequest, 
        @PathVariable Long id 
    ) {
         return ResponseEntity.ok(
            ProductResponse.builder()
            .name(productRequest.getName())
            .description(productRequest.getDescription())
            .price(BigDecimal.ONE)
            .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
        String fieldName = ((FieldError) objectError).getField();
        String errorMessage = objectError.getDefaultMessage();
        errors.put(fieldName, errorMessage);
        });
        return ErrorResponse.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .message(errors.toString())
            .timestamp(LocalDateTime.now())
            .build();
    }
    
}
