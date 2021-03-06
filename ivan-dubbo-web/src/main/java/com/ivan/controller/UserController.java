package com.ivan.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ivan.User;
import com.ivan.api.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference(version = "1.0.1")
    private UserService demoService;

    @RequestMapping("/")
    public String goIndex() {
        logger.info("test   index");
        return "index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<User> getUsers() {

        System.out.println((demoService == null) + "-----------");
        return demoService.getUsers();
    }

    @RequestMapping("/one/{bid}")
    @ResponseBody
    public User getUserById(@PathVariable String bid) {
        return demoService.getUserByPrimaryKey(bid);
    }
}
