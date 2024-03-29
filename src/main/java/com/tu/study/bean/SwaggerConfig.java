package com.tu.study.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * SwaggerConfig
 *
 * @author 屠永建
 * @version 1.0
 * 2021/11/13 22:52
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createApi(){
        //在swagger请求中给每一个请求提娜佳一个token
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameterBuilder.name("token").description("令牌")
                .modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        parameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).
                select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("tuyongjian swagger")
                .description("学习用的swagger")
                .version("1.0")
                .build();
    }
}
