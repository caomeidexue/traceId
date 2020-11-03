package com.local.study.bistory.ps;

import java.util.Arrays;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/10/30 11:29 上午
 */
public class PsInfo {
    private String user;
    private int pid;
    private String command;
    private String[] params;

    public PsInfo(String user, int pid, String command, String[] params) {
        this.user = user;
        this.pid = pid;
        this.command = command;
        this.params = params;
    }

    public String getUser() {
        return user;
    }

    public int getPid() {
        return pid;
    }

    public String getCommand() {
        return command;
    }

    public String[] getParams() {
        return params;
    }

    @Override
    public String toString() {
        return "PsInfo{" +
                "user='" + user + '\'' +
                ", pid=" + pid +
                ", command='" + command + '\'' +
                ", params=" + Arrays.toString(params) +
                '}';
    }
}
