package com.sdl.times.common.security.handle;

import com.alibaba.fastjson.JSON;
import com.sdl.times.common.constant.HttpStatus;
import com.sdl.times.common.model.Result;
import com.sdl.times.common.utils.ServletUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AcessDecisionHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ServletUtil.renderString(httpServletResponse, JSON.toJSONString(Result.error( HttpStatus.FORBIDDEN,"没有权限")));
    }
}
