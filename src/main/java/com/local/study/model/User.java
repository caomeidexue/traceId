package com.local.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/8/12 10:35 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private String userName;

    private String id;

    private Integer age;

}
