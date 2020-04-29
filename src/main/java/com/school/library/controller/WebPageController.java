package com.school.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WebPageController {


    @RequestMapping("/login")
    public String login (   ){
        return "login";
    }
    @RequestMapping("/")
    public String login2 (   ){
        return "login";
    }

    @RequestMapping("/index")
    public String index (   ){
        return "indexAdmin";
    }
}
