package com.tu.study.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

/**
 * @author tuyongjian
 * @date 2023/3/22 19:51
 */
@Data
@Builder
@Document(indexName = "medcl")
public class Medcl {

    @Id
    private String id;

    /**
     * Keyword  表示该字段内容是一个文本并作为一个整体不可分，默认建立索引
     */
    @Field(type = FieldType.Keyword,analyzer = "pinyin_analyzer",searchAnalyzer = "pinyin_analyzer")
    private String name;


}
