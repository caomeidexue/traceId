package com.local.study.bistory.jps;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/10/30 2:38 下午
 */
public class Res<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
