package com.tu.study.controller;

import com.tu.study.bean.ElasticSearchConfig;
import com.tu.study.dto.EsStudentDto;
import com.tu.study.serviceimpl.EsSearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author 屠永建
 * @version 1.0
 * 2021/11/14 19:59
 **/
@RequestMapping(value = "/es")
@RestController
@Slf4j
public class ElasticSearchController {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Resource
    private EsSearchService esSearchService;

    @GetMapping(value = "/testRestHighLevelClient")
    public void testRestHighLevelClient(){
        //测试保存
        IndexRequest request = new IndexRequest("posts");
        request.id("1");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);
        IndexResponse index= null;
        try {
            index = restHighLevelClient.index(request, ElasticSearchConfig.COMMON_OPTIONS);
        } catch (IOException e) {

        }
        log.info("index-------------:{}", index);
    }

    @PostMapping(value = "/addStudent")
    public EsStudentDto addStudent(@RequestBody EsStudentDto esStudentDto){
        return esSearchService.addStudent(esStudentDto);
    }

    @GetMapping(value = "/getAllStudent")
    public List<EsStudentDto> getAllStudent(){
        return esSearchService.getAllStudent();
    }

    @GetMapping(value = "/findStudent")
    public EsStudentDto findStudent(@RequestParam(value = "sName") String sName ){
        return esSearchService.findStudent(sName);
    }

    @PostMapping(value = "/searchStudent")
    public List<EsStudentDto> searchStudent(@RequestBody EsStudentDto esStudentDto){
        return esSearchService.searchStudent(esStudentDto);
    }
    @PostMapping(value = "/restTemplateStudent")
    public List<EsStudentDto> restTemplateStudent(@RequestBody EsStudentDto esStudentDto){
        return esSearchService.restTemplateStudent(esStudentDto);
    }

    @PostMapping(value = "/highlightBuilder")
    public List<EsStudentDto> highlightBuilder(@RequestBody EsStudentDto esStudentDto){
        return esSearchService.highlightBuilder(esStudentDto);
    }
    @GetMapping(value = "/searchPinYin")
    public List<EsStudentDto> searchPinYin(@RequestParam(value = "keyword") String keyword){
        return esSearchService.searchPinYin(keyword);
    }

}
