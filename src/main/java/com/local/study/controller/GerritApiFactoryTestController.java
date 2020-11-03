package com.local.study.controller;

import com.urswolfer.gerrit.client.rest.http.GerritRestClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/9/6 8:19 下午
 */
@RestController
@RequestMapping(value = "/testScope1")
public class GerritApiFactoryTestController {
    @Resource
    private GerritFactory gerritFactory;


    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public void userProfile(@PathVariable("username") String username) {

        try {
            for (int i = 0; i < 100; i++) {
                GerritRestClient amdim = gerritFactory.getGerritRestClient("amdim" + i);
                System.out.println(Thread.currentThread().getId() + "amdim:" + amdim);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}
