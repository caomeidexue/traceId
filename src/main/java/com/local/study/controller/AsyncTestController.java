package com.local.study.controller;

import com.local.study.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/9 3:55 下午
 */
@RestController
public class AsyncTestController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/testAsync")
    public Object testAsync(){
        for (int i = 0; i < 50; i++) {
            System.out.println(getClass().getName()+"-------"+Thread.currentThread().getName());
            asyncService.testAsync();
        }
        return new HashMap<>();
    }


    @RequestMapping("/testChangeParameters")
    public Object testChangeParameters(){

        asyncService.testChangeParameters("11");

        List<String> list = Arrays.asList("bb","dd");
        String[] objects = (String[]) list.toArray();
        asyncService.testChangeParameters(objects);
        String [] strParma = {"aa","bb","cc"};
        asyncService.testChangeParameters(strParma);

        return new HashMap<>();
    }
}
