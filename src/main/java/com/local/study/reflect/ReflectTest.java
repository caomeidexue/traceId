package com.local.study.reflect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/12/9 5:50 下午
 */
public class ReflectTest {

    public static void main(String[] args) throws Exception{
       /* User jiang = new User("jiang", 10);
        Method reflect = User.class.getDeclaredMethod("reflect");
        reflect.setAccessible(true);
        reflect.invoke(jiang);
        System.out.println(1);*/

        List<Integer> list = Arrays.asList(1, 2, 3);
        list.forEach(id->{
            if (1 == id){
                return;
            }
            System.out.println(id);
        });

        System.out.println("aaa");

    }


   static class User {
        private String name;

        private Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public void reflect(){
            System.out.println(this.name+"-----"+this.age);
        }
    }
}
