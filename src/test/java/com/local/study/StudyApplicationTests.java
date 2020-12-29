package com.local.study;

import com.local.study.annotation.MyAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotationUtils;

//@SpringBootTest
//@MyAnnotation(value = "aa", alias = "bb")
class StudyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testCC() {
        MyAnnotation ann = getClass().getAnnotation(MyAnnotation.class);
        System.out.println(ann.value());
        System.out.println(ann.alias());
        System.out.println(1);

    }

    @Test
    void testC2() {
        MyAnnotation ann = AnnotationUtils.findAnnotation(getClass(),
                MyAnnotation.class);
        System.out.println(ann.value());
        System.out.println(ann.alias());
        System.out.println(1);


    }

    @Test
    void test11() {
        System.out.println(1);
    }

}
