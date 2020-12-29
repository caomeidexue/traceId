package com.local.study.objectsize;

import org.apache.lucene.util.RamUsageEstimator;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/13 5:50 下午
 */
public class ObjectSizeTest {




    public static void main(String[] args) {
        TestController testController = new TestController();
        testController.setTestService(new TestServiceImpl());
        ////计算指定对象及其引用树上的所有对象的综合大小，单位字节
        long l = RamUsageEstimator.sizeOf(testController);

        //计算指定对象本身在堆空间的大小，单位字节
        long b = RamUsageEstimator.shallowSizeOf(testController);

        //计算指定对象及其引用树上的所有对象的综合大小，返回可读的结果，如：2KB
        String string = RamUsageEstimator.humanSizeOf(testController);

        System.out.println(l);
    }
}
