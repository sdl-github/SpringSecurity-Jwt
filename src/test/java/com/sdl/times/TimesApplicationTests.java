package com.sdl.times;

import com.sdl.times.common.security.service.JwtService;
import com.sdl.times.common.utils.RedisCache;
import com.sdl.times.system.service.impl.MenuServiceImpl;
import com.sdl.times.system.service.impl.RoleServiceImpl;
import com.sdl.times.system.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = TimesApplication.class)
class TimesApplicationTests {
    @Resource
    private UserServiceImpl userService;
    @Resource
    private RoleServiceImpl roleService;
    @Resource
    RedisCache redisCache;
    @Resource
    JwtService jwtService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MenuServiceImpl menuServiceImpl;
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
    void testBcypassword(){
       String a = bCryptPasswordEncoder.encode("123456");
       System.out.println(a);
     }

     @Test
    void testmenu(){
        // Set<String> strings = menuService.findMenuByUserName("admin");
         //Set<String> per = menus.stream().map(Menu::getApiUrl).collect(Collectors.toSet());

       //  System.out.println(strings);
     }
    @Test
    void testapiurl(){
//        //Set<String> per = menus.stream().map(Menu::getApiUrl).collect(Collectors.toSet());

      //  System.out.println(strings);
    }
}
