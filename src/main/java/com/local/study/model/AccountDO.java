package com.local.study.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/9/4 5:27 下午
 */
@Data
public class AccountDO implements Serializable {

    private static final long serialVersionUID = 6630055998172480394L;

    private String userName;

    private String passWord;

}
