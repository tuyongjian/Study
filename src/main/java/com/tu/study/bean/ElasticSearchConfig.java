package com.tu.study.bean;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 屠永建
 * @version 1.0
 * 2021/11/14 19:17
 * @Description
 **/
@Configuration
public class ElasticSearchConfig {

    @Value("${elasticsearch.ip}")
    private String ipPort;

    public static final  RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        COMMON_OPTIONS = builder.build();
    }

    @Bean
    public RestClientBuilder restClientBuilder(){
        return RestClient.builder(makeHttpHost(ipPort));
    }

    @Bean
    public RestHighLevelClient restHighLevelClient(@Autowired RestClientBuilder restClientBuilder){
        return new RestHighLevelClient(restClientBuilder);
    }

    private HttpHost makeHttpHost(String s){
        String[] address = s.split(":");
        String ip = address[0];
        String port = address[1];
        return new HttpHost(ip,Integer.parseInt(port),"http");
    }

}
