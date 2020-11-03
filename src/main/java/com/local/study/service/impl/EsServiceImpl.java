package com.local.study.service.impl;

import com.alibaba.fastjson.JSON;
import com.local.study.model.User;
import com.local.study.service.EsService;
import lombok.AllArgsConstructor;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 〈功能概述〉<br>
 *
 * @className: ESServiceImpl
 * @author: yiche
 * @date: 2020/8/12 10:07 上午
 */
@Service
@AllArgsConstructor
public class EsServiceImpl implements EsService {

    private final RestHighLevelClient restHighLevelClient;
    private final String indexName = "indexname";

    @Override
    public Object getMessageById(String id) {
        try {
            GetRequest request = new GetRequest(indexName, id);
            GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
            return response.getSource();
        } catch (IOException e) {
            return null;
        }

    }

    @Override
    public Object save(String id) {
        int i = 0;
        while (true) {
            User user = new User();
            user.setAge(10);
            user.setId(id + i);
            user.setUserName("test" + i);
            String json = JSON.toJSONString(user);
            IndexRequest request = new IndexRequest(indexName).id(id + i).source(json, XContentType.JSON);
            try {
                restHighLevelClient.index(request, RequestOptions.DEFAULT);
                i++;
                if (i == 10000) {
                    System.gc();
                    System.out.println("fullGC-----------------");
                }
                System.out.println(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
