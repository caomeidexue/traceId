package com.local.study.java8.springjava8.model;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/17 11:18 上午
 */
public class Profile {

    public Profile() {
    }

    public Profile(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
