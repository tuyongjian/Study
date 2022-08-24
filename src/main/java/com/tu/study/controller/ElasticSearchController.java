package com.tu.study.controller;

import com.tu.study.bean.ElasticSearchConfig;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 屠永建
 * @version 1.0
 * 2021/11/14 19:59
 **/
@RestController
@Slf4j
public class ElasticSearchController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public void contextLoads() throws IOException {
        //测试保存
        IndexRequest request = new IndexRequest("posts");
        request.id("1");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);
        IndexResponse index=restHighLevelClient.index(request, ElasticSearchConfig.COMMON_OPTIONS);
        log.info("index-------------:{}", index);
    }
}
