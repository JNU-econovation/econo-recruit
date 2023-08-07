package com.econovation.recruit.adapter.in.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected Object handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        return e.getBindingResult().getAllErrors();
    }

    @ExceptionHandler(NullPointerException.class)
    protected Object handleMethodUsernameNotFoundExceptionException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        return e.getBindingResult().getAllErrors();
    }
}
