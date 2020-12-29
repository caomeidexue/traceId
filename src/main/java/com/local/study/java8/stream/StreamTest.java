package com.local.study.java8.stream;


import com.local.study.java8.profiler.Profiler;
import org.omg.CORBA.INTERNAL;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/17 10:23 上午
 */
public class StreamTest {

    public static void main(String[] args) {
        String [] arr = {"@profile.activeaaa","@profile.activebbb"};
        //把arr的每个元素当成参数 传递给 Profiler构造器 创建一个对象
        List<Profiler> collect = Arrays.stream(arr).map(Profiler::new).collect(Collectors.toList());
        System.out.println(collect);

        //通过
        Integer[] allPass = {1,1,1,1} ;
        //不通过
        Integer[] allNoPass = {2,2,2,2};
        Integer[] noPass= {2,1,3,1};
        // 疑似
        Integer[] susspect ={1,3,1,3};
        String allPassStr = testAnyMatch(allPass);
        String allNopassStr = testAnyMatch(allNoPass);
        String noPassStr = testAnyMatch(noPass);
        String supectStr = testAnyMatch(susspect);
        System.out.println(111);


    }

    /**
     * 校验
     */
    public static String  testAnyMatch(Integer [] arr){
         //通过
         Integer[] allPass = {1,1,1,1} ;
         //不通过
         Integer[] allNoPass = {2,2,2,2};
         Integer[] noPass= {2,1,3,1};
         // 疑似
         Integer[] susspect ={1,3,1,3};

        boolean allPassFlag = Stream.of(arr).allMatch(s -> s.equals(1));
        boolean unpass = Stream.of(arr).anyMatch(s -> s.equals(2));

        if (allPassFlag){
            System.out.println("通过");
            return "通过";
        }
        if (unpass){
            System.out.println("不通过");
            return "不通过";
        }
        System.out.println("疑似");
        return "疑似";
    }
}
