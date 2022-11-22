package com.tu.study.transactional;

import com.tu.study.dao.CfgMetaDataDTO;
import com.tu.study.dao.ConfigMetaDataDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author tuyongjian
 * @date 2022/11/22 15:09
 */
@Service
public class ServicePropagation {


    @Resource
    private ServiceB serviceB;

    @Resource
    private ConfigMetaDataDao configMetaDataDao;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void testREQUIRED(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("REQUIRED-A");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);

        serviceB.testTransactionalREQUIRED();
    }

   // @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void testSUPPORTS(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("SUPPORTS-A");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);

        serviceB.testTransactionalSUPPORTS();
    }

    //@Transactional(propagation = Propagation.MANDATORY,rollbackFor = Exception.class)
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void testMANDATORY(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("MANDATORY-A");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);

        serviceB.testTransactionalMANDATORY();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void testREQUIRES_NEW(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("REQUIRES_NEW-A");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        serviceB.testTransactionalREQUIRES_NEW();
        int i = 1/0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void testNOT_SUPPORTED(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("NOT_SUPPORTED-A");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        serviceB.testTransactionalNOT_SUPPORTED();
        int i = 1/0;
    }

    //@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void testNEVER(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("NEVER-A");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        serviceB.testTransactionalNEVER();
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void testNESTED(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("NESTED-A");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        try {
            serviceB.testTransactionalNESTED();
        }catch (Exception e){
        }


        cfgMetaDataDto.setCode("NESTED-A1");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
    }
}
