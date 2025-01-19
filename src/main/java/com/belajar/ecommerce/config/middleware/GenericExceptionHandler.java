/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.ecommerce.config.middleware;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.belajar.ecommerce.common.errors.BadRequestException;
import com.belajar.ecommerce.common.errors.ResourceNotFoundException;
import com.belajar.ecommerce.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author firman
 */

@ControllerAdvice
@Slf4j
public class GenericExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException exception) {
        return ErrorResponse.builder()
        .code(HttpStatus.NOT_FOUND.value())
        .message(exception.getMessage())
        .timestamp(LocalDateTime.now())
        .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleBadRequestException(HttpServletRequest request, BadRequestException exception) {
        return ErrorResponse.builder()
        .code(HttpStatus.NOT_FOUND.value())
        .message(exception.getMessage())
        .timestamp(LocalDateTime.now())
        .build();
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse handleGenericErrorException(HttpServletRequest request, Exception exception) {
        log.error("status code = " + HttpStatus.INTERNAL_SERVER_ERROR + "error message = " + exception.getMessage());
        
        return ErrorResponse.builder()
        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message(exception.getMessage())
        .timestamp(LocalDateTime.now())
        .build();
    }
}
