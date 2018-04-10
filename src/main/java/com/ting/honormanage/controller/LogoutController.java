package com.ting.honormanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author nitmali@126.com
 * @date 2018/4/10 17:12
 */
@Controller
public class LogoutController {
    @RequestMapping(value = "/api/logout")
    public String managerLogout(HttpServletRequest request) {
        System.err.println("get");
        request.getSession().invalidate();
        return "/home.html";
    }
}