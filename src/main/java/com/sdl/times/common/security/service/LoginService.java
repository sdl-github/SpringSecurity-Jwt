package com.sdl.times.common.security.service;
import com.sdl.times.common.exception.CustomException;
import com.sdl.times.common.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
@Component
public class LoginService {
    @Autowired
    protected AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    public String login(String username,String password){
        Authentication authentication = null;
        try{
            // 该方法会去调用MyUserDetailService.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (Exception e){
            if (e instanceof BadCredentialsException){
                throw new CustomException("用户名或密码错误");
            }else {
                throw new CustomException(e.getMessage());
            }
        }
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        return jwtService.createToken(user);
    }
}
