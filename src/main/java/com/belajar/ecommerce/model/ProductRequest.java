/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.ecommerce.model;

import java.math.BigDecimal;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class ProductRequest {
    @NotBlank(message = "nama produk tidak boleh kosong")
    @Size(min = 2, max = 100, message = "nama produk minimal 2 karakter, maximal 100 karakter")
    String name;

    @NotNull(message = "harga tidak boleh kosong")
    @Positive(message = "harga tidak boleh 0")
    @Digits(integer = 10, fraction = 2, message = "harga maksimal 10 digit dengan 2 angka di belakang koma")
    BigDecimal price;

    @NotNull(message = "deskripsi produk tidak boleh kosong")
    @Size(max = 1000, message = "deskripsi produk tidak boleh lebih dari 1000 karakter")
    String description;
}
