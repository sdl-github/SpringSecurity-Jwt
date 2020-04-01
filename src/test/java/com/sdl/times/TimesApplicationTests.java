package com.sdl.times;

import com.sdl.times.common.security.model.SecurityUser;
import com.sdl.times.common.security.service.JwtService;
import com.sdl.times.common.utils.RedisCache;
import com.sdl.times.system.entity.Role;
import com.sdl.times.system.entity.User;
import com.sdl.times.system.service.RoleService;
import com.sdl.times.system.service.UserService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = TimesApplication.class)
class TimesApplicationTests {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    RedisCache redisCache;
    @Resource
    JwtService jwtService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    void testrole() {
        System.out.println(roleService.findRoleByUserId(1));
    }
    @Test
    void testredis(){
        redisCache.setCacheObject("code","sdl",10, TimeUnit.MINUTES);
        String a = redisCache.getCacheObject("code");
        System.out.println(a);
    }
    @Test
    void testjwt(){
        User user = new User();
        user.setUsername("sdl");
        SecurityUser securityUser = new SecurityUser(user,null);
        String token = jwtService.createToken(securityUser);
        System.out.println("qianduantoken"+token);
        String usernamefromtoken = jwtService.getUsernameFromToken(token);
        System.out.println("usernamefromtoken"+usernamefromtoken);
        Claims claims = jwtService.parseToken(token);
        String toenKey = (String) claims.get(Claims.ID);
        System.out.println("redistokenkey"+toenKey);
        SecurityUser redisuser = redisCache.getCacheObject(toenKey);
        System.out.println(redisuser);
        System.out.println( redisuser.getUsername());
 }
     @Test
    void testBcypassword(){
       String a = bCryptPasswordEncoder.encode("123456");
       System.out.println(a);
     }
}
