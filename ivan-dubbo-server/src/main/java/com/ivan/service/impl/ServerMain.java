package com.ivan.service.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author lichangtong
 * @create 2017-08-21 19:49
 **/
public class ServerMain {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring-registry.xml"});
        context.start();

        System.out.println("按任意键退出！！！");
        System.in.read(); // 按任意键退出
    }
}
