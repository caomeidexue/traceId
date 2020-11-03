package com.local.study.bistory.ps;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/10/30 11:17 上午
 */
public abstract class ClosableProcess extends Process implements Closeable {

    private final Process delegate;

    private final long id;

    ClosableProcess(Process delegate) {
        this.delegate = delegate;
        this.id = JavaProcesses.register(delegate);
    }

    @Override
    public OutputStream getOutputStream() {
        return delegate.getOutputStream();
    }

    @Override
    public InputStream getInputStream() {
        return delegate.getInputStream();
    }

    @Override
    public InputStream getErrorStream() {
        return delegate.getErrorStream();
    }

    @Override
    public int waitFor() throws InterruptedException {
        return delegate.waitFor();
    }

    @Override
    public int exitValue() {
        return delegate.exitValue();
    }

    @Override
    public void destroy() {
        delegate.destroy();
        JavaProcesses.remove(id);
    }

    public abstract byte[] read() throws Exception;

    @Override
    public void close() {
        destroy();
    }
}