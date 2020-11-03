package com.local.study.controller;

import com.google.gerrit.extensions.api.GerritApi;
import com.urswolfer.gerrit.client.rest.GerritApiImpl;
import com.urswolfer.gerrit.client.rest.GerritAuthData;
import com.urswolfer.gerrit.client.rest.GerritRestApiFactory;
import com.urswolfer.gerrit.client.rest.http.GerritRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/9/4 4:48 下午
 */
@Component
public class GerritFactory {

    @Value("${gerritUrl}")
    private String gerritUrl;

    private final GerritRestApiFactory gerritRestApiFactory = new GerritRestApiFactory();

    /**
     * 该方法在提供api的接口上是用
     *
     * @param userName
     * @param
     * @return
     */
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public GerritApi getGerritApi(String userName) {
        // TODO: 2020/9/4
        //根据用户名获取密码
        //String passWord = getPassWord();
        userName = "admin";
        String passWord = "y3JWbSbaQm0xk+Cc7l3RtUvhmZwebRsf/4d5e6QFXw";
        checkPassWord(passWord);
        GerritAuthData.Basic authData = new GerritAuthData.Basic(gerritUrl, userName, passWord);
        GerritApi gerritApi = gerritRestApiFactory.create(authData);
        return gerritApi;
    }

    /**
     * 校验password
     *
     * @param passWord
     */
    private void checkPassWord(String passWord) {
        if (StringUtils.isEmpty(passWord)) {
            throw new RuntimeException("gerritAPI CRUD passWord can't be null or empty ");
        }
    }


    /**
     * 该方法在非原生api提供的调用
     *
     * @param userName
     * @param
     * @return
     */
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public GerritRestClient getGerritRestClient(String userName) {
        //String passWord = getPassWord();
        userName = "gerrit";
        String passWord = "gerrit";
        checkPassWord(passWord);
        GerritAuthData.Basic authData = new GerritAuthData.Basic(gerritUrl, userName, passWord);
        GerritApi gerritApi = gerritRestApiFactory.create(authData);
        GerritApiImpl gerritApiImpl = (GerritApiImpl) gerritApi;
        GerritRestClient gerritRestClient = (GerritRestClient) gerritApiImpl.restClient();
        return gerritRestClient;
    }
}
