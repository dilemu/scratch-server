package com.example.server.conifg;

import com.example.server.exception.BizBaseException;
import com.example.server.model.vo.JsonResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * @author
 * @Description 全局异常
 * @date 2020/9/17 14:25
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult<Boolean> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return JsonResult.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(BizBaseException.class)
    public JsonResult<Boolean> handleRuntimeException(BizBaseException e) {
        return JsonResult.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public JsonResult<Boolean> handleException(Exception e) {
        return JsonResult.error(e.getMessage());
    }
}
