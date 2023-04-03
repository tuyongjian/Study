package com.tu.study.controller;

import com.tu.study.dto.EsStudentDto;
import com.tu.study.dto.Medcl;
import com.tu.study.serviceimpl.EsSearchHighLevelClientService;
import com.tu.study.serviceimpl.EsSearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.SearchHit;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    private EsSearchService esSearchService;

    @Resource
    private EsSearchHighLevelClientService esSearchHighLevelClientService;

    @GetMapping(value = "/testRestHighLevelClientCreate")
    public void testRestHighLevelClient(){
        esSearchHighLevelClientService.createIndex();
    }

    @PostMapping(value = "/testRestHighLevelClientbulkRequest")
    public void testRestHighLevelClientbulkRequest(@RequestBody List<Medcl> list){
        esSearchHighLevelClientService.bulkRequestTest(list);
    }

    @GetMapping(value = "/testRestHighLevelClientUpdate")
    public void testRestHighLevelClientUpdate(){
        esSearchHighLevelClientService.update();
    }

    @GetMapping(value = "/testRestHighLevelClientDelete")
    public void testRestHighLevelClientDelete(){
        esSearchHighLevelClientService.delete();
    }

    @GetMapping(value = "/testRestHighLevelClientDeleteByQueryRequest")
    public void testRestHighLevelClientDeleteByQueryRequest(@RequestParam String name){
        esSearchHighLevelClientService.deleteByQueryRequestTest(name);
    }
    @GetMapping(value = "/testRestHighLevelClientBulkDiffRequest")
    public void testRestHighLevelClientbulkDiffRequest(){
        esSearchHighLevelClientService.bulkDiffRequestTest();
    }
    @GetMapping(value = "/testRestHighLevelClientSelectByName")
    public SearchHit[] testRestHighLevelClientbulkDiffRequest(@RequestParam String name){
        return esSearchHighLevelClientService.selectByName(name);
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

    @GetMapping(value = "/fuzzyStudent")
    public List<EsStudentDto> fuzzyStudent(@RequestParam(value = "sName") String sName ){
        return esSearchService.fuzzyStudent(sName);
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



    @PostMapping(value = "/addUser")
    public Medcl addUser(@RequestBody Medcl medcl){
        return esSearchService.addUser(medcl);
    }

    @GetMapping(value = "/searchPinYin")
    public List<Medcl> searchPinYin(@RequestParam(value = "keyword") String keyword){
        return esSearchService.searchPinYin(keyword);
    }

}
