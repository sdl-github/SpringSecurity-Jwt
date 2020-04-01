package com.sdl.times.common.security.handle;

import com.alibaba.fastjson.JSON;
import com.sdl.times.common.constant.HttpStatus;
import com.sdl.times.common.model.Result;
import com.sdl.times.common.security.DynamicPermission;
import com.sdl.times.common.utils.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationEntryPointImpl.class);
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info(e.getMessage());
        ServletUtil.renderString(httpServletResponse, JSON.toJSONString(Result.error( HttpStatus.UNAUTHORIZED,"认证失败，无法访问系统资源")));
    }
}
