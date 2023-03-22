package com.tu.study.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * @author tuyongjian
 * @date 2023/3/10 11:21
 */
@Data
@Builder
@Document(indexName = "student")
public class EsStudentDto {

    @Id
    @Field(store = true, type = FieldType.Keyword)
    private String sId;

    @Field(store = true, type = FieldType.Keyword)
    private String sName;

    @Field(store = true, type = FieldType.Text, analyzer = "pinyin_analyzer")
    //Text可以分词 ik_smart=粗粒度分词 ik_max_word 为细粒度分词
    private String sAddress;

    @Field(index = false, store = true, type = FieldType.Integer)
    private Integer sAge;

    @Field(index = false, store = true, type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date sCreateTime;

    @Field(type = FieldType.Keyword)
    private String[] sCourseList; //数组类型 由数组中第一个非空值决定(这里数组和集合一个意思了)

    @Field(type = FieldType.Keyword)
    private List<String> sColorList; //集合类型 由数组中第一个非空值决定

}
