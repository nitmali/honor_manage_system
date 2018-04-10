package com.ting.honormanage.model;

import org.springframework.stereotype.Component;

/**
 * @author nitmali@126.com
 * @date 2018/4/10 10:50
 */

@Component
public class UserModel {
    private String password;

    private String userName;

    private String userType;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
