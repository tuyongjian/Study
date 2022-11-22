package com.tu.study.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface ConfigMetaDataDao {


    int addConfigMetaData(CfgMetaDataDTO cfgMetaDataDto);
}
