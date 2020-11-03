package com.local.study.bistory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/3 4:05 下午
 */
public class PidUtils {

    private static final Logger logger = LoggerFactory.getLogger(PidUtils.class);

    private static final List<PidHandler> PID_HANDLERS = initPidHandler();

    private static List<PidHandler> initPidHandler() {
        List<PidHandler> handlers = Lists.newArrayList();

        handlers.add(new PidBySystemPropertyHandler());

        if (Boolean.parseBoolean(System.getProperty("bistoury.pid.handler.jps.enable", "true"))) {
            handlers.add(new PidByJpsHandler());
        }

        if (Boolean.parseBoolean(System.getProperty("bistoury.pid.handler.ps.enable", "true"))) {
            handlers.add(new PidByPsHandler());
        }

        ServiceLoader<PidHandlerFactory> handlerFactories = ServiceLoader.load(PidHandlerFactory.class);
        for (PidHandlerFactory factory : handlerFactories) {
            handlers.add(factory.create());
        }
        Collections.sort(handlers, new Comparator<PidHandler>() {
            @Override
            public int compare(PidHandler o1, PidHandler o2) {
                return Integer.compare(o1.priority(), o2.priority());
            }
        });
        return ImmutableList.copyOf(handlers);
    }

    public static int getPid() {
        for (PidHandler handler : PID_HANDLERS) {
            int pid = handler.getPid();
            if (pid > 0) {
                logger.info("get pid by {} success, pid is {}", handler.getClass().getSimpleName(), pid);
                return pid;
            }
        }
        return -1;
    }
}
