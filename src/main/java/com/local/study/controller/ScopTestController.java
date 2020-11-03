package com.local.study.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/9/6 8:15 下午
 * https://www.cnblogs.com/heyanan/p/12054840.html
 */
@RestController
@RequestMapping(value = "/testScope")
public class ScopTestController {

    private String name;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public void userProfile(@PathVariable("username") String username) {
        name = username;
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getId() + "name:" + name);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}
