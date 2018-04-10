package com.ting.honormanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author nitmali
 */
@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "/home.html";
    }
}
