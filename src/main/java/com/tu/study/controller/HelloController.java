package com.tu.study.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author 屠永建
 * @version 1.0
 * 2021/11/13 23:00
 **/
@Api("hello")
@RestController
public class HelloController {

    @ApiOperation(value = "desc of method", notes = "")
    @GetMapping(value="/hello")
    @Cacheable
    public Object hello( /* 参数注解 */ @ApiParam(value = "desc of param" , required=true ) @RequestParam String name) {
        return "Hello " + name + "!";
    }
}
