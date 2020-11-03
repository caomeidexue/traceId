package com.local.study.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/9/4 5:26 下午
 */
@Data
public class ReviewDO extends AccountDO implements Serializable {

    private static final long serialVersionUID = 6595640311718484037L;
    /**
     * 修改提交的唯一标识
     */
    private String changeId;

    /**
     * 数据信息
     */
    private String message;
}
