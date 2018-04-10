package com.ting.honormanage.model;

import com.ting.honormanage.entity.CheckerInfo;
import org.springframework.stereotype.Component;

/**
 * @author nitmali@126.com
 * @date 2018/4/9 19:28
 */

@Component
public class CheckerInfoModel {
    private Long id;

    private String username;

    private String password;

    private String name;

    private String phone;

    private String authority;

    public CheckerInfoModel() {
    }

    public CheckerInfoModel(CheckerInfo checkerInfo) {
        this.id = checkerInfo.idOfCheckerInfoModel();
        this.username = checkerInfo.usernameOfCheckerInfoModel();
        this.password = null;
        this.name = checkerInfo.nameOfCheckerInfoModel();
        this.phone = checkerInfo.phoneOfCheckerInfoModel();
        this.authority = checkerInfo.authorityOfCheckerInfoModel();
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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }



    public Long idOfCheckerInfo() {
        return id;
    }

    public String usernameOfCheckerInfo() {
        return username;
    }

    public String passwordOfCheckerInfo() {
        return password;
    }

    public String nameOfCheckerInfo() {
        return name;
    }

    public String phoneOfCheckerInfo() {
        return phone;
    }

    public CheckerInfo.Authority authorityOfCheckerInfo() {
        String authorityFIRST_LEVEL = "一级权限";
        String authoritySECOND_LEVEL = "二级权限";
        if (authorityFIRST_LEVEL.equals(this.authority)) {
            return CheckerInfo.Authority.FIRST_LEVEL;
        } else if (authoritySECOND_LEVEL.equals(this.authority)) {
            return CheckerInfo.Authority.SECOND_LEVEL;
        } else {
            return null;
        }
    }


}
