package com.tu.study.esmapper;

import com.tu.study.dto.Medcl;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author tuyongjian
 * @date 2023/3/10 13:48
 */

public interface EsUserMapper extends ElasticsearchRepository<Medcl,String> {

}
