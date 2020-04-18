package com.sdl.times.common.security.filter;

import com.sdl.times.common.security.model.SecurityUser;
import com.sdl.times.common.security.service.JwtService;
import com.sdl.times.common.utils.SecurityUtil;
import com.sdl.times.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Autowired
    private JwtService jwtService;

    /**
     * 每次请求都要执行
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException, AuthenticationException {
        //从request中获得tokenkey，从redis中拿到loginuser
        SecurityUser loginUser = null;
        loginUser = jwtService.getLoginUser(request);
        Authentication authentication =  SecurityUtil.getAuthentication();
        //判断用户不为空，且SecurityContextHolder授权信息还是空的,
        if (StringUtil.isNotNull(loginUser) && StringUtil.isNull(authentication)){
            //验证检查刷新userKey时间。。。
            jwtService.verifySecurity(loginUser);
            // 将用户信息存入 authentication，方便后续校验
            UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
