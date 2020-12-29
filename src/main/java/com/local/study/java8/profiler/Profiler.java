package com.local.study.java8.profiler;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/17 10:26 上午
 */
public class Profiler {

    public Profiler(String name) {
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
