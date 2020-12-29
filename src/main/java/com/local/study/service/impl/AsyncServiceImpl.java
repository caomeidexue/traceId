package com.local.study.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.local.study.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/9 3:57 下午
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async
    public void testAsync() {
        String name = Thread.currentThread().getName();
        System.out.println("异步线程名称:"+name);
    }

    @Override
    public void testChangeParameters(String... values) {
        if (values.length ==0){
            System.out.println("参数不能为空");
        }

        List<String> list = Arrays.asList(values);
        System.out.println(JSONObject.toJSONString(list));
    }
}
