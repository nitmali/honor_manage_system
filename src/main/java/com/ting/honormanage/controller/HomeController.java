package com.ting.honormanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author nitmali
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index( ) {
        return "/index";
    }



}
