package com.local.study.controller;

import com.local.study.service.EsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 〈功能概述〉<br>
 * @author: yiche
 * @date: 2020/8/12 10:06 上午
 */
@RestController
@Slf4j
public class EsController {

    @Resource
    private EsService esService;

    @GetMapping("getMessage")
    public Object getMessage(@RequestParam String id){
      log.info("getMessage-param:{}",id);
      Object object =  esService.getMessageById(id);
      return object;
    }

    @RequestMapping("save")
    public Object save(@RequestParam String id){
        Object object =  esService.save(id);
        return object;
    }

}
