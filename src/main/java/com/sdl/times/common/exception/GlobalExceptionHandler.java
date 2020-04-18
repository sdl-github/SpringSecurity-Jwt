package com.sdl.times.common.exception;

import com.sdl.times.common.model.Result;
import com.sdl.times.common.security.service.MyUserDetailService;
import com.sdl.times.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static com.sdl.times.common.constant.HttpStatus.BAD_METHOD;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public Result customException(CustomException e) {
        log.info(e.getMessage(),e.getCode());
        if (StringUtil.isNull(e.getCode())) {
            return Result.error(e.getMessage());
        }
        return Result.error(e.getCode(), e.getMessage());
    }
    /**
     * 请求方法不支持
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e){
        return Result.error(BAD_METHOD,"请求方法不支持");
    }
}
