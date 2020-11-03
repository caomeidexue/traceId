package com.local.study.controller;

import com.local.study.service.FullGcTestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/8/14 4:56 下午
 */
@RestController
@RequestMapping
public class FullGcTestController {

    @Resource
    FullGcTestService fullGcTestService;

    @RequestMapping("testGC")
    public Object testGC() {
        fullGcTestService.testGC();
        return "success";
    }
}
