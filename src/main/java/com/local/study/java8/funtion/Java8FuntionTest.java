package com.local.study.java8.funtion;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/13 10:42 上午
 */
public class Java8FuntionTest {

    public static void main(String[] args) {
        java8User user = new java8User();
        user.setName("jiang");
        user.setAge(10);
        List<java8User> java8Users = new ArrayList<>();
        java8Users.add(user);
        java8Users.stream().map(java8User::getAge).collect(Collectors.toList());


        int parallelism = -1;



    }
}
