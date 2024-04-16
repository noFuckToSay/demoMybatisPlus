package com.example.demoMybatisPlus.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import java.io.IOException;


@RestControllerAdvice
@Slf4j
public class ValidExceptonHandler {

//    @ExceptionHandler({MethodArgumentNotValidException.class, ServletException.class,IOException.class})
//    public String validExceptionHandler(MethodArgumentNotValidException ex) {
//        return ex.getBindingResult().getFieldError().getDefaultMessage();
//    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public String HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) throws IOException {
//        return "请求参数类型不匹配";
//    }

    @ExceptionHandler(RuntimeException.class)
    public AjaxResult RuntimeExceptionHandler(RuntimeException ex) throws IOException {
        return AjaxResult.error(ex.getMessage());
    }
}
