package com.ting.honormanage.controller;

import com.ting.honormanage.entity.ManagerInfo;
import com.ting.honormanage.model.UserModel;
import com.ting.honormanage.repository.ManagerRepository;
import org.apache.catalina.Manager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author nitmali@126.com
 * @date 2018/4/10 10:44
 */

@RestController
public class LoginController {
    @Resource
    private ManagerRepository managerRepository;

    @PostMapping("/post_manager_login")
    public String managerLogin(@RequestBody UserModel userModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ManagerInfo managerInfo = managerRepository.findManagerInfoByUsername(userModel.getUserName());
        if(managerInfo == null)
        {
            return "{\"message\":\"user not find\"}";
        }else if(managerInfo.getPassword().equals(userModel.getPassword())){
            session.setAttribute("userType", userModel.getUserType());
            session.setAttribute("userName", userModel.getUserName());
            return "{\"message\":\"login success\"}";
        }else {
            return "{\"message\":\"password error\"}";
        }
    }

}
