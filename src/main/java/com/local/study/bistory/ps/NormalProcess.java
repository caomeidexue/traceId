package com.local.study.bistory.ps;

import com.google.common.util.concurrent.RateLimiter;

import java.io.InputStream;
import java.util.Arrays;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/10/30 11:23 上午
 */
public class NormalProcess extends ClosableProcess {
    private static final int BUF_SIZE = 4 * 1024;

    private static final byte[] EMPTY_BYTES = new byte[0];

    private final RateLimiter rateLimiter = RateLimiter.create(16); //限制每秒read的次数

    private final byte[] buffer = new byte[BUF_SIZE];

    NormalProcess(Process delegate) {
        super(delegate);
    }

    @Override
    public byte[] read() throws Exception {
        rateLimiter.acquire();
        InputStream inputStream = getInputStream();
        int count = inputStream.read(buffer);
        if (count > 0) {
            return Arrays.copyOfRange(buffer, 0, count);
        } else if (count == 0) {
            return EMPTY_BYTES;
        } else {
            return null;
        }
    }
}
