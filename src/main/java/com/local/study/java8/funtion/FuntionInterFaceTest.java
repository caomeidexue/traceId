package com.local.study.java8.funtion;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/8/26 10:24 上午
 */
public class FuntionInterFaceTest {
    public static void main(String[] args) {
        FuntionInterFaceTest test = new FuntionInterFaceTest();
        int result = test.compute(1, a -> a * 8);
        int compute = test.compute(2, b -> b + 5);
        String convert = test.convert(2, a -> a.toString());

        // resultCopose =7; 执行流程 1*2 = 2 然后 2+5 =7
        int resultCopose = test.computeCompose(1, a -> a + 5, a -> a * 2);
        // computeAndThen = 12  执行流程 1+5 = 6 ,然后6*2 = 12
        int computeAndThen = test.computeAndThen(1, a -> a + 5, a -> a * 2);


        //一下测试biFunction 接口
        int computeBiFunction = test.computeBiFunction(2, 3, (a, b) -> a + b);
        //15
        int computeBiFunction2 = test.computeBiFunction2(2, 3, (a, b) -> a + b,a->a*3);


        System.out.println(computeBiFunction2);
    }

    public int compute(int a, Function<Integer,Integer> function){
        Integer apply = function.apply(a);
        return apply;
    }

    public String convert(int a, Function<Integer,String> function){
        String apply = function.apply(a);
        return apply;
    }

    public int computeCompose(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        // 先执行 function2 在执行 function1
        return function1.compose(function2).apply(a);
    }

    public int computeAndThen(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        // 先执行 function1 在执行 function2
        return function1.andThen(function2).apply(a);
    }

    public int computeBiFunction(int a , int b , BiFunction<Integer,Integer,Integer> biFunction){
        return biFunction.apply(a,b);
    }

    public int computeBiFunction2(int a , int b , BiFunction<Integer,Integer,Integer> biFunction,Function<Integer,Integer> function){
        return biFunction.andThen(function).apply(a,b);
    }
}
