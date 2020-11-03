package com.local.study.bistory;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/3 4:08 下午
 */
public abstract class AbstractPidHandler implements PidHandler{


    @Override
    public final int getPid() {
        try {
            return doGetPid();
        } catch (Exception e) {
            System.out.println("get pid error"+ getClass().getName());
            return -1;
        }
    }

    protected abstract int doGetPid();
}
