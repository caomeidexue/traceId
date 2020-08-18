package com.local.study.controller;

import com.local.study.annotation.RequestUser;
import com.local.study.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/8/17 6:10 下午
 */
@RestController
@RequestMapping
public class HandlerMethodArgumentResolverController {
    @PostMapping("/testHandler")
    public Object testObject(Integer aaa, @RequestParam Integer userId , @RequestUser(name = "userId") Integer id){

        System.out.println(aaa);
        System.out.println(userId);
        return "success";
    }


}
