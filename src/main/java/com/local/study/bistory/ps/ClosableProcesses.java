package com.local.study.bistory.ps;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/10/30 11:19 上午
 */
public class ClosableProcesses {


    public static ClosableProcess wrap(Process process) {
        if (isUnixProcess(process)) {
            return new UnixProcess(process);
        } else {
            return new NormalProcess(process);
        }
    }

    private static boolean isUnixProcess(Process process) {
        try {
            Class<? extends Process> clazz = process.getClass();
            return clazz.getName().equals("java.lang.UNIXProcess");
        } catch (Exception e) {
            return false;
        }
    }

}

