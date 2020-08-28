package com.local.study.localtest.string;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/8/26 5:24 下午
 */
public class StringFormatTest {

    public static void main(String[] args) {
        String format = String.format("jiang%s", "-wagn");
        char charA = 'a';
        String format1 = String.format("字母A：%c", charA);
        System.out.println(format);
    }
}
