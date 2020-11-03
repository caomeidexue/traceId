package com.local.study.bistory.ps;

import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.io.ByteStreams;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/10/30 11:16 上午
 */
public class Test {




    private static final String TOMCAT_USER = "yiche";
    //private static final String TOMCAT_COMMAND = META_STORE.getStringProperty("tomcat.command", "/home/java/default/bin/java");
    private static final String TOMCAT_COMMAND = "/home/java/default/bin/java";

    private static final int USER_INDEX = 0;
    private static final int PID_INDEX = 1;
    private static final int COMMAND_INDEX = 10;

    public static void main(String[] args) {

        int  i = doGetPid();


        System.out.println(i);

    }


    protected static int doGetPid() {
        String psInfo = getPsInfo();
        if (!Strings.isNullOrEmpty(psInfo)) {
            ArrayListMultimap<String, PsInfo> multimap = parsePsInfo(psInfo);
            List<PsInfo> infos = multimap.get(TOMCAT_COMMAND);
            if (infos != null && infos.size() > 0) {
                for (PsInfo info : infos) {
                    if (TOMCAT_USER.equalsIgnoreCase(info.getUser())) {
                        return info.getPid();
                    }
                }
            }
        }
        return -1;
    }


    private static ArrayListMultimap<String, PsInfo> parsePsInfo(final String psInfo) {
        ArrayListMultimap<String, PsInfo> multimap = ArrayListMultimap.create();
        String all = psInfo.replaceAll("[( )\t]+", " ");
        String[] lines = all.split("[\n\r(\r\n)]");
        for (String line : lines) {
            if (Strings.isNullOrEmpty(line)) {
                continue;
            }
            String[] pieces = line.split(" ");
            final String user = pieces[USER_INDEX];
            final int pid = Integer.parseInt(pieces[PID_INDEX]);
            final String command = pieces[COMMAND_INDEX];
            final String[] params = Arrays.copyOfRange(pieces, COMMAND_INDEX + 1, pieces.length);
            PsInfo process = new PsInfo(user, pid, command, params);
            multimap.put(command, process);
        }
        return multimap;
    }

    private static String getPsInfo() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ClosableProcess process = ClosableProcesses.wrap(new ProcessBuilder("/bin/sh", "-c", "ps aux | grep java").redirectErrorStream(true).start());
             InputStream inputStream = process.getInputStream()) {
            ByteStreams.copy(inputStream, outputStream);
            return outputStream.toString("utf8");
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
