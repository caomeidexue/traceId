package com.local.study.objectsize;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/13 5:51 下午
 */
public class TestController {

    private TestService TestService;

    private int a ;

    private int [] arr = new int[1024*1024];


    public com.local.study.objectsize.TestService getTestService() {
        return TestService;
    }

    public void setTestService(com.local.study.objectsize.TestService testService) {
        TestService = testService;
    }


    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
