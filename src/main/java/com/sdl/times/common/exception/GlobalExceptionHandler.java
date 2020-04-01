package com.sdl.times.common.exception;

import com.sdl.times.common.constant.HttpStatus;
import com.sdl.times.common.exception.user.MyAccessDeniedException;
import com.sdl.times.common.model.Result;
import com.sdl.times.common.utils.StringUtil;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public Result businessException(CustomException e) {
        if (StringUtil.isNull(e.getCode())) {
            return Result.error(e.getMessage());
        }
        return Result.error(e.getCode(), e.getMessage());
    }
}
