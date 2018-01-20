package com.springmvc.controller;


import com.springmvc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/home")
    @ResponseBody
    public String index() {
        return "This is accessible only to admin";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

}
