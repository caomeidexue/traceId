package com.local.study.config.gerrit;

import com.google.gerrit.extensions.api.GerritApi;
import com.google.gson.JsonElement;
import com.urswolfer.gerrit.client.rest.GerritApiImpl;
import com.urswolfer.gerrit.client.rest.GerritAuthData;
import com.urswolfer.gerrit.client.rest.GerritRestApiFactory;
import com.urswolfer.gerrit.client.rest.RestClient;
import com.urswolfer.gerrit.client.rest.http.GerritRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/9/2 9:19 下午
 */
//@Configuration
public class GerritConfig {

    @Value("${gerritUrl}")
    private String gerritUrl;

    @Value("${gerritUname}")
    private String gerritUname;

    @Value("${gerritPW}")
    private String gerritPW;

    @Bean
    public GerritApi getGerritApi() {
        GerritRestApiFactory gerritRestApiFactory = new GerritRestApiFactory();
        GerritAuthData.Basic authData = new GerritAuthData.Basic(gerritUrl, gerritUname, gerritPW);
        GerritApi gerritApi = gerritRestApiFactory.create(authData);
        return gerritApi;
    }


}
