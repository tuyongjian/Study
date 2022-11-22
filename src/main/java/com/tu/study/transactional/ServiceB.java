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
@Service
public class ServiceB {

    @Resource
    private ConfigMetaDataDao configMetaDataDao;

    //@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void testTransactionalREQUIRED(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("REQUIRED-B");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        int i = 1/0;
    }

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    public void testTransactionalSUPPORTS(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("SUPPORTS-B");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        int i = 1/0;
    }
    @Transactional(propagation = Propagation.MANDATORY,rollbackFor = Exception.class)
    public void testTransactionalMANDATORY(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("MANDATORY-B");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        int i = 1/0;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void testTransactionalREQUIRES_NEW(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("REQUIRES_NEW-B");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED,rollbackFor = Exception.class)
    public void testTransactionalNOT_SUPPORTED(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("NOT_SUPPORTED-B");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        int i = 1/0;
    }
    @Transactional(propagation = Propagation.NEVER,rollbackFor = Exception.class)
    public void testTransactionalNEVER(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("NEVER-B");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
    }
    @Transactional(propagation = Propagation.NESTED,rollbackFor = Exception.class)
    public void testTransactionalNESTED(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("NESTED-B");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        int i = 1/0;
    }
}
