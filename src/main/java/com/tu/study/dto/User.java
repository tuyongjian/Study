package com.tu.study.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * @author tuyongjian
 * @date 2023/3/22 19:51
 */
@Mapping(mappingPath = "elasticsearch_setting.json")
@Setting(settingPath = "elasticsearch_mapping.json")
@Data
@Builder
@Document(indexName = "user")
public class User {

    @Id
    private String name;

    @Field
    private String sex;

    @Field
    private Integer age;
}
