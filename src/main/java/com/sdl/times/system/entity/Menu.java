package com.sdl.times.system.entity;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (Menu)实体类
 *
 * @author sdl
 * @since 2020-04-09 17:46:43
 */
public class Menu implements Serializable, Comparable<Menu> {
    private static final long serialVersionUID = -87720321632292123L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 图标
    */
    private String icon;
    /**
    * 资源名称
    */
    private String name;
    /**
    * 上级ID
    */
    private Integer pid;
    /**
    * 排序
    */
    private Integer sort;
    /**
    * 组件
    */
    private String component;
    /**
    * 访问地址
    */
    private String apiUrl;
    /**
    * 类型 menu、button
    */
    private String type;
    /**
    * 是否隐藏
    */
    private Integer hidden;
    /**
    * 创建时间
    */
    private Date gmtCreate;

    private List<Menu> children;

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", sort=" + sort +
                ", component='" + component + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                ", type='" + type + '\'' +
                ", hidden=" + hidden +
                ", gmtCreate=" + gmtCreate +
                ", children=" + children +
                '}';
    }

    @Override
    public int compareTo(Menu o) {
        return this.getSort().compareTo(o.getSort());
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

}