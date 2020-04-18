package com.sdl.times.common.security;

import com.sdl.times.common.security.filter.JwtAuthenticationTokenFilter;
import com.sdl.times.common.security.handle.AcessDecisionHandlerImpl;
import com.sdl.times.common.security.handle.AuthenticationEntryPointImpl;
import com.sdl.times.common.security.handle.LogoutSuccessHandlerImpl;
import com.sdl.times.common.security.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 认证失败处理
     */
    @Autowired
    private AuthenticationEntryPointImpl unauthentication;
    /**
     * 拒绝访问处理
     */
    @Autowired
    private AcessDecisionHandlerImpl acessDecisionHandler;
    /**
     * 请求之前拦截拦截token
     */
    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;
    /**
     * 用户身份认证处理
     */
    @Autowired
    private MyUserDetailService userDetailService;
    /**
     * Bcr密码encoder
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * 动态权限控制
     */
    @Autowired
    private DynamicPermission dynamicPermission;
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    /**
     * 解决AuthenticationManager无法注入
     * 在LoginService中
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CRSF禁用，因为不使用session
        http.csrf().disable()
                // 认证失败处理类
                .exceptionHandling().authenticationEntryPoint(unauthentication)
                .accessDeniedHandler(acessDecisionHandler)
                .and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 过滤请求
                .authorizeRequests()
                // 允许匿名访问
                .antMatchers("/auth/login").anonymous()
                // 认证后授权访问
                .antMatchers("/auth/getInfo","/auth/getMenu").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().access("@dynamicPermission.checkPermission(request,authentication)")//动态匹配菜单权限
                .and()
                //不拦截iframe
                .headers().frameOptions().disable()
                .and()
                // 添加JWT filter
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
    }
    /**
     * 忽略拦截url或静态资源文件夹
     *  web.ignoring() 忽略
     *  http.permitAll() 允许该路径通过
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET,
                "/favicon.ico",
                "/**/*.png",
                "/**/*.ttf",
                "/*.html",
                "/**/*.css",
                "/**/*.js");
    }
    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 身份认证接口
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
    }


}
