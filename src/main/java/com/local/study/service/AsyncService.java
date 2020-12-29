package com.local.study.service;

import org.springframework.scheduling.annotation.Async;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/9 3:56 下午
 */
public interface AsyncService {


  void  testAsync();

  void testChangeParameters(String ... values);
}
