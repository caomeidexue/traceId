package com.local.study.controller;

import com.google.gerrit.extensions.api.GerritApi;
import com.urswolfer.gerrit.client.rest.GerritRestApi;
import com.urswolfer.gerrit.client.rest.RestClient;
import com.urswolfer.gerrit.client.rest.http.GerritRestClient;
import com.urswolfer.gerrit.client.rest.tools.Tools;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabMergeRequest;
import org.gitlab.api.models.GitlabProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/9/2 5:11 下午
 */
@RestController
public class GitlabApiController {

    //@Autowired
    private GitlabAPI gitlabAPI;


    @RequestMapping("/gitlabapi")
    public Object gitlabApiTest(@RequestParam String projectId, @RequestParam Integer mergeRequestLid) throws IOException {

        List<GitlabProject> ownedProjects = gitlabAPI.getOwnedProjects();
        GitlabProject gitlabProject = ownedProjects.get(1);
        String nameWithNamespace = gitlabProject.getPathWithNamespace();
        String name = gitlabProject.getName();
        GitlabProject project = gitlabAPI.getProject(nameWithNamespace, name);
        return project;

    }


}
