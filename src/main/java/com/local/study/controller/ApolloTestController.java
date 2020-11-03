package com.local.study.controller;

import com.ctrip.framework.apollo.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/10/14 5:42 下午
 */
@RestController
@RequestMapping("/")
public class ApolloTestController {



    @Value("${aaa:我是}")
    public String apolloConfigA;

    @Value("${bbb:我是}")
    public String apolloConfigB;

    @RequestMapping("/getApolloValueByKey")
    public Object getApolloValueByKey(@RequestParam String key){
        if (key.equals("a")){
            System.out.println(apolloConfigA);
            return apolloConfigA;
        }
        if (key.equals("b")){
            System.out.println(apolloConfigB);
            Map<String,Object> map = new HashMap<>();
            map.put("apolloConfigB",apolloConfigB);
            return map;
        }
        return null;
    }
}
