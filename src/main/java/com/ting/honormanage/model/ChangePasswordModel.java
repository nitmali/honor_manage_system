package com.ting.honormanage.model;

import org.springframework.stereotype.Component;

/**
 * @author nitmali@126.com
 * @date 2018/4/10 20:30
 */

@Component
public class ChangePasswordModel {
    private String oldPassword;

    private String newPassword;

    public ChangePasswordModel() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
