package com.ivan.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ivan.User;
import com.ivan.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String goIndex() {
        return "index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<User> getUsers() {

        System.out.println((userService==null)+"-----------");
        return userService.getUsers();
    }

    @RequestMapping("/one")
    @ResponseBody
    public User getUserById() {
        return userService.getUserByPrimaryKey("1");
    }
}
