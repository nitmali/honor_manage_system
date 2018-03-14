package com.ting.honormanage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
    @GetMapping("/")
    public String index(ModelAndView modelAndView) {
        return "这是一个HomeControlle测试，你好呀小黑猪！";
    }
}
