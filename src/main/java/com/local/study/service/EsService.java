package com.local.study.service;

/**
 * 〈功能概述〉<br>
 *
 * @className: ESService
 * @author: yiche
 * @date: 2020/8/12 10:07 上午
 */
public interface EsService {
    /**
     * 根据ID查询数据
     *
     * @param id
     * @return
     */
    Object getMessageById(String id);

    /**
     * 存储数据
     *
     * @param id
     * @return
     */
    Object save(String id);
}
