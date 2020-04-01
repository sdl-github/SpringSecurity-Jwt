package com.sdl.times.system.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-03-24 15:16:59
 */
public class User implements Serializable {
    private static final long serialVersionUID = 967938088409475580L;
    /**
    * 主键ID
    */
    private Integer id;
    /**
    * 账号
    */
    private String username;
    /**
    * 登录密码
    */
    private String password;
    /**
    * 昵称
    */
    private String nickName;
    /**
    * 性别 0:男 1:女
    */
    private String sex;
    /**
    * 手机号码
    */
    private String phone;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 头像
    */
    private String avatar;
    /**
    * 状态 1启用，0禁用
    */
    private Boolean enabled;
    /**
    * 创建时间
    */
    private Date gmtCreate;
    /**
    * 更新时间
    */
    private Date gmtModified;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

}