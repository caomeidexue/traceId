package com.local.study.bistory.jps;

import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;

import java.util.List;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/10/30 2:37 下午
 */
public class JpsTest {

    private static final Splitter SPLITTER = Splitter.on(" ").trimResults().omitEmptyStrings();

    private static final String JPS_SYMBOL_CLASS = System.getProperty("bistoury.pid.handler.jps.symbol.class", "bistoury-test.jar");
    private static final int JPS_PID_INDEX = 0;

    private static final int JPS_CLASS_INDEX = 1;



    public static void main(String[] args) {
        int i = doGetPid();
        System.out.println(i);
    }


    protected static int doGetPid() {
        Res<List<String>> res = getJpsInfo();
        if (res.getCode() == 0) {
            ArrayListMultimap<String, JpsInfo> multimap = parseJpsInfo(res.getData());
            List<JpsInfo> jpsInfos = multimap.get(JPS_SYMBOL_CLASS);
            if (jpsInfos.size() > 0) {
                return jpsInfos.iterator().next().getPid();
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    private static Res<List<String>> getJpsInfo() {
        Res<List<String>> res = new Res<>();
        Jps.executeJps(new String[]{"-l"}, res);
        return res;
    }

    private static ArrayListMultimap<String, JpsInfo> parseJpsInfo(List<String> jpsInfos) {
        ArrayListMultimap<String, JpsInfo> multimap = ArrayListMultimap.create();
        for (String jpsInfo : jpsInfos) {
            List<String> list = SPLITTER.splitToList(jpsInfo);
            if (list.size() == 2) {
                final int pid = Integer.parseInt(list.get(JPS_PID_INDEX));
                final String clazz = list.get(JPS_CLASS_INDEX);
                multimap.put(clazz, new JpsInfo(pid, clazz));
            }
        }
        return multimap;
    }
}
