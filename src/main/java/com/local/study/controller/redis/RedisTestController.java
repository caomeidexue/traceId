package com.local.study.controller.redis;

import com.local.study.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/8/26 4:26 下午
 */
@RestController
public class RedisTestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("getString")
    public Object getStringKey(@RequestParam String key){

        ValueOperations<String,List<User>> valueOperations = redisTemplate.opsForValue();
        List<User> list = valueOperations.get(key);

        if (list == null){
            List<User> dataList = new ArrayList<>();
            dataList.add(new User("jiang","1",10));
            dataList.add(new User("wang","2",15));
            dataList.add(new User("qi","3",20));
            valueOperations.set(key,dataList,10, TimeUnit.SECONDS);
            return dataList;
        }else {
            return list;
        }

    }


    @RequestMapping("hash")
    public Object getHash(@RequestParam String key){

        HashOperations hashOperations = redisTemplate.opsForHash();
        Map<String,String> map = new HashMap<>();
        map.put("jiangKey","jiangValue");
        map.put("wangKey","wangValue");
        hashOperations.putAll(key,map);
        Object jiang = hashOperations.get(key, "jiangKey");
        List values = hashOperations.values(key);
        Map entries = hashOperations.entries(key);
        Boolean wang = hashOperations.hasKey(key, "wangKey");
        Set keys = hashOperations.keys(key);


        return  "success";

    }
}
