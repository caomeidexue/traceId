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

        String replace = replace();
        System.out.println(replace);
    }


    public static String replace(){
        String command = "appconfig -pid$$FILLPID$$";
        int pid = 96273;
        String  FILL_PID = "$$FILLPID$$";
       return command.replace(FILL_PID, String.valueOf(pid));
    }
}
