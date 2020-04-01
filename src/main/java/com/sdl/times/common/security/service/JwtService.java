package com.sdl.times.common.security.service;

import com.sdl.times.common.constant.Constants;
import com.sdl.times.common.security.model.SecurityUser;
import com.sdl.times.common.utils.RedisCache;
import com.sdl.times.common.utils.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Component
public class JwtService {
    @Autowired
    private RedisCache redisCache;
    // 令牌自定义标识
    private String header = "Authorization";
    // 令牌秘钥
    private String secret = "abcdefghijklmnopqrstuvwxyz";
    // 令牌有效期（默认30分钟）
    private int expireTime = 30;
    protected static final long MILLIS_SECOND = 1000;
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;
    /**
     * 创建令牌
     *
     * @param securityUser 用户信息
     * @return 令牌
     */
    public String createToken(SecurityUser securityUser)
    {
        String tokenKey = UUID.randomUUID().toString();
        securityUser.setTokenKey(tokenKey);
        saveToken(securityUser);
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, securityUser.getUsername());
        claims.put(Claims.ID, tokenKey);
        return createToken(claims);
    }
    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims)
    {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }
    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public SecurityUser getLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = getToken(request);
        System.out.println("tokenkey是"+token);
        if (StringUtil.isNotEmpty(token))
        {
            Claims claims = parseToken(token);
            // 解析对应的权限以及用户信息
            String tokenKey = (String) claims.get(Claims.ID);
//            String userKey = getTokenKey(uuid);
            SecurityUser user = redisCache.getCacheObject(tokenKey);
            return user;
        }
        return null;
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    public String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(header);
//        if (StringUtil.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX))
//        {
//            token = token.replace(Constants.TOKEN_PREFIX, "");
//        }
        return token;
    }
    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token)
    {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }
    /**
     * 保存令牌
     */
    public void saveToken(SecurityUser securityUser)
    {
        String key = securityUser.getTokenKey();
        redisCache.setCacheObject(key, securityUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(String tokenKey)
    {
        if (StringUtil.isNotEmpty(tokenKey))
        {
            redisCache.deleteObject(tokenKey);
        }
    }
}
