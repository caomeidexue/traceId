package com.local.study.bistory.jps;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/10/30 2:38 下午
 */
public class JpsInfo {
    private int pid;
    private String clazz;

    public JpsInfo(int pid, String clazz) {
        this.clazz = clazz;
        this.pid = pid;
    }

    public String getClazz() {
        return clazz;
    }

    public int getPid() {
        return pid;
    }
}
