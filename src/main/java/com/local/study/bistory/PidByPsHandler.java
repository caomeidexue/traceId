package com.local.study.bistory;

import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.io.ByteStreams;
import com.local.study.bistory.ps.ClosableProcess;
import com.local.study.bistory.ps.ClosableProcesses;
import com.local.study.bistory.ps.PsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/3 4:19 下午
 */
public class PidByPsHandler extends AbstractPidHandler implements PidHandler{

    private static final Logger logger = LoggerFactory.getLogger(PidByPsHandler.class);

    //private static final MetaStore META_STORE = MetaStores.getMetaStore();

    //private static final String TOMCAT_USER = META_STORE.getStringProperty("tomcat.user", "tomcat");
    private static final String TOMCAT_USER = "root";
    //private static final String TOMCAT_COMMAND = META_STORE.getStringProperty("tomcat.command", "/home/java/default/bin/java");
    private static final String TOMCAT_COMMAND = "/home/java/default/bin/java";

    private static final int USER_INDEX = 0;
    private static final int PID_INDEX = 1;
    private static final int COMMAND_INDEX = 10;

    @Override
    public int priority() {
        return Priority.FROM_PS_PRIORITY;
    }

    @Override
    protected int doGetPid() {
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
            logger.error("execute ps aux|grep java error", e);
            return null;
        }
    }
}
