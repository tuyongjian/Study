package com.tu.study.esmapper;

import com.tu.study.dto.EsStudentDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author tuyongjian
 * @date 2023/3/10 13:48
 */

public interface EsStudentMapper extends ElasticsearchRepository<EsStudentDto,String> {

    EsStudentDto findBysName(String sName);
}
