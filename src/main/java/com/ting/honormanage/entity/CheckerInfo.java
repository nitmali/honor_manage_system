package com.ting.honormanage.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author nitmali@126.com
 * @date 2018/3/14 15:10
 */
@Entity
public class CheckerInfo {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(length = 11)
    private String phone;

    @Column
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public void setCheckerInfo(CheckerInfo checkerInfo)
    {
        this.id = checkerInfo.id;
        this.username = checkerInfo.username;
        this.password = checkerInfo.password;
        this.name = checkerInfo.name;
        this.phone = checkerInfo.phone;
        this.authority = checkerInfo.authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public enum Authority
    {
        //一级权限
        FIRST_LEVEL,
        //二级权限
        SECOND_LEVEL
    }
}
