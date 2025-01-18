/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.ecommerce.controller;

import org.springframework.web.bind.annotation.RestController;
import com.belajar.ecommerce.common.errors.BadRequestException;
import com.belajar.ecommerce.common.errors.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * @author firman
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
    
    @GetMapping("/bad-request")
    public ResponseEntity<String> badRequestError() {
        throw new BadRequestException("ada error bad request");
    }

    @GetMapping("/generic-error")
    public ResponseEntity<String> genericError() {
        throw new RuntimeException("generic error");
    }

    @GetMapping("/not-found")
    public ResponseEntity<String> notFoundError() {
        throw new ResourceNotFoundException("data tidak ditemukan");
    }
}
