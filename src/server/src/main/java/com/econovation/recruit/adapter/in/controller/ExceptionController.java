package com.econovation.recruit.adapter.in.controller;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        return e.getBindingResult().getAllErrors();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected Object handleMethodUsernameNotFoundExceptionException(MethodArgumentNotValidException e, HttpServletRequest request) {
        return e.getBindingResult().getAllErrors();
    }
}