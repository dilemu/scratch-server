package com.example.demo.conifg;

import com.example.demo.model.vo.JsonResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author
 * @Description 全局异常
 * @date 2020/9/17 14:25
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult<Boolean> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return  JsonResult.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public JsonResult<Boolean> handleRuntimeException(RuntimeException e) {
        return  JsonResult.error(e.getMessage());
    }
}
