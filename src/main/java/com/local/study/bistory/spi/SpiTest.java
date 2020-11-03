package com.local.study.bistory.spi;

import com.local.study.bistory.PidByPsHandler;
import com.local.study.bistory.PidHandler;
import com.local.study.bistory.PidHandlerFactory;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/3 4:36 下午
 */
public class SpiTest implements PidHandlerFactory {

    @Override
    public PidHandler create() {
        return new PidByPsHandler();
    }
}
