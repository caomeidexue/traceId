package com.local.study.java8;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/8/20 10:58 上午
 */
public class BreakPointsTest {

    public static void main(String[] args) {
        int i = 0;
        for (int j = 0; j < 10; j++) {
            if (j ==9){
                System.out.println(j);
            }
            System.out.println(j);
        }
    }
}
