package com.local.study.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/9/28 6:43 下午
 */
public class Test {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Date from = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            System.out.println(from+"-------"+i);
        }


    }
}
