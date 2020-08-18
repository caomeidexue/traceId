package com.local.study.service.impl;

import com.local.study.service.FullGcTestService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/8/14 4:57 下午
 */
@Service
public class FullGcTestServiceImpl implements FullGcTestService {


    @Override
    public void testGC() {

        while (true){
           int number = new Random().nextInt(10) + 1;
            System.out.println(number);
           if (number == 5){
               System.gc();
               System.out.println("FUllGC");
           }
        }
    }
}
