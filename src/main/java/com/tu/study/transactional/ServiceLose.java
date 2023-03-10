package com.tu.study.transactional;

import com.tu.study.dao.CfgMetaDataDTO;
import com.tu.study.dao.ConfigMetaDataDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author tuyongjian
 * @date 2022/11/22 10:43
 */
//@Service
public class ServiceLose {

    @Resource
    private ConfigMetaDataDao configMetaDataDao;

    @Transactional(rollbackFor = Exception.class)
    public void testTransactionalA() throws Exception {
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("TRA");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        throw new Exception("事务失效");
    }

    @Transactional(rollbackFor = Exception.class)
    void testTransactionalB() throws Exception {
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("TRA");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        throw new Exception("事务失效");
    }

    @Transactional(rollbackFor = Exception.class)
    public void testTransactionalC() throws Exception {
        try {
            CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
            cfgMetaDataDto.setCode("TRA");
            configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
            throw new Exception("事务失效");
        }catch (Exception e){

        }

    }



    public void testTransactionalD() throws Exception {
        insert();
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert() throws Exception {
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("TRA");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        throw new Exception("事务失效");
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NOT_SUPPORTED)
    public void testTransactionalE() throws Exception {
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("TRA");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        throw new Exception("事务失效");
    }

}
