package com.local.study.bistory;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/3 4:24 下午
 */
public class PidBySystemPropertyHandler extends AbstractPidHandler {

    @Override
    public int priority() {
        return Priority.FROM_SYSTEM_PROPERTY_PRIORITY;
    }

    @Override
    protected int doGetPid() {
        return Integer.valueOf(System.getProperty("bistoury.user.pid", "-1"));
    }
}
