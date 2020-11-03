package com.local.study.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gerrit.extensions.api.GerritApi;
import com.google.gerrit.extensions.api.changes.ChangeApi;
import com.google.gerrit.extensions.api.projects.ProjectApi;
import com.google.gerrit.extensions.common.ChangeInfo;
import com.google.gerrit.extensions.common.MergeableInfo;
import com.google.gerrit.extensions.restapi.RestApiException;
import com.google.gson.JsonElement;
import com.local.study.model.ReviewDO;
import com.local.study.service.GitlabService;
import com.urswolfer.gerrit.client.rest.GerritApiImpl;
import com.urswolfer.gerrit.client.rest.RestClient;
import com.urswolfer.gerrit.client.rest.http.GerritRestClient;
import com.urswolfer.gerrit.client.rest.http.changes.FileApiRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/9/2 4:01 下午
 */

@RestController
public class GerritApiController {


    @Autowired
    private GerritFactory gerritFactory;


    /**
     * 集成gerrit
     *
     * @param changeId
     * @return
     * @throws Exception
     */
    @RequestMapping("/changeid")
    public Object getByChangeId(@RequestParam String changeId) throws Exception {
        GerritApi gerritApi = gerritFactory.getGerritApi("gerrit");
        List<ChangeInfo> changes = gerritApi.changes().query("status:merged").get();
        List<ChangeInfo> changes1 = gerritApi.changes().query("status:open").get();
        ProjectApi testPro6 = gerritApi.projects().name("testPro6");
        System.out.println(changes);
        return changes;
    }

    @RequestMapping("/aaa")
    public Object test(@RequestParam String changeId) throws Exception {
        String request = String.format("%1$s%2$s%3$s", "/changes/", changeId, "/revisions/current/mergeable");
        GerritRestClient gerritRestClient = gerritFactory.getGerritRestClient("gerrit");
        JSONObject reqBody = new JSONObject();
        JsonElement result = gerritRestClient.requestJson(request, reqBody.toString(), RestClient.HttpVerb.GET);
        String asString = result.getAsString();
        return asString;
    }


    /**
     * 评论提交接口
     * RestClient.HttpVerb.POST 必须为POST请求
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/review")
    public Object review(@RequestBody ReviewDO reviewDO) throws Exception {
        //String request = String.format("%1$s%2$s%3$s", "/changes/", "testPro6~110", "/revisions/3/review");
        String request = String.format("%1$s%2$s%3$s", "/changes/", reviewDO.getChangeId(), "/revisions/1/review");
        GerritRestClient gerritRestClient = gerritFactory.getGerritRestClient(reviewDO.getPassWord());
        JSONObject reqBody = new JSONObject();
        reqBody.put("drafts", "PUBLISH_ALL_REVISIONS");
        reqBody.put("message", reviewDO.getMessage());
        JsonElement result = gerritRestClient.requestJson(request, reqBody.toString(), RestClient.HttpVerb.POST);
        JSONObject jsonObject = JSONObject.parseObject(result.toString());
        return jsonObject;
    }


    /**
     * 提交接口
     * RestClient.HttpVerb.POST 必须为POST请求
     * http://192.168.15.177:8081/a/changes/testPro6~118/revisions/1/submit
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/submit")
    public Object submit(@RequestBody ReviewDO reviewDO) {
        String uri = String.format("%1$s%2$s%3$s%4$s%5$s", "/changes/", reviewDO.getChangeId(), "/revisions/", 1, "/submit");
        GerritRestClient gerritRestClient = gerritFactory.getGerritRestClient(reviewDO.getPassWord());
        JsonElement result = null;
        try {
            result = gerritRestClient.postRequest(uri);
        } catch (RestApiException e) {
            String message = e.getMessage();
            System.out.println(message);
            // e.printStackTrace();
        }
        return "aaaa";
    }

}
