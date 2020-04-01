package com.sdl.times.common.security.handle;

import com.alibaba.fastjson.JSON;
import com.sdl.times.common.constant.HttpStatus;
import com.sdl.times.common.model.Result;
import com.sdl.times.common.security.model.SecurityUser;
import com.sdl.times.common.security.service.JwtService;
import com.sdl.times.common.utils.ServletUtil;
import com.sdl.times.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private JwtService jwtService;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {
        SecurityUser loginUser = jwtService.getLoginUser(request);
        if (StringUtil.isNotNull(loginUser)){
            String userName = loginUser.getUsername();
            jwtService.delLoginUser(jwtService.getToken(request));
        }
        ServletUtil.renderString(response, JSON.toJSONString(Result.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
