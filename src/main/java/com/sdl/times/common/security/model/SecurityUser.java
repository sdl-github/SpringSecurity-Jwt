package com.sdl.times.common.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdl.times.system.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails  {

    private String userName;
    private String passWord;
    private List<Role> roles;
    private String userKey;
    /**
     * 登陆时间 单位毫秒
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;
    public List<Role> getRoles() {
        return roles;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public SecurityUser() {}

    public SecurityUser(String userName, String passWord, List<Role> roles) {
        this.userName = userName;
        this.passWord = passWord;
        this.roles = roles;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return passWord;
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return userName;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
