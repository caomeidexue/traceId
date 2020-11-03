package com.local.study.config.gitlabapi;

import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/9/3 10:00 上午
 */
//@Configuration
public class GitLabApiConfig {

    private String host = "http://192.168.70.138:8082";

    private String gitlabUName = "root";

    private String gitlabPD = "Yiche_123";

    @Bean
    public GitlabAPI getGitlabAPI() throws IOException {
        GitlabSession session = GitlabAPI.connect(host, gitlabUName, gitlabPD);
        GitlabAPI api = GitlabAPI.connect(host, session.getPrivateToken());
        return api;
    }
}
