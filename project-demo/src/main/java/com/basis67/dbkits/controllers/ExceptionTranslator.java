package com.basis67.dbkits.controllers;

import com.basis67.dbkits.dto.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<String> handleBindException(BindException e) {
        return ApiResult.error(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<String> handleException(Exception e) {

        e.printStackTrace();

        return ApiResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
