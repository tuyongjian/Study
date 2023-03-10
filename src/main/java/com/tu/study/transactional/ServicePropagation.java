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

    /**
     * A方法调用B方法；如果A方法有事务，则B方法加入A方法的事务，如果A方法没有事务，则B方法自己新建一个事务。
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void testREQUIRED(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("REQUIRED-A");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);

        serviceB.testTransactionalREQUIRED();
    }

    /**
     * A方法调用B方法；如果A方法有事务，则B加入A方法的事务，如果A方法没有事务，则B方法以非事务方式运行。
     */
    //@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void testSUPPORTS(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("SUPPORTS-A");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);

        serviceB.testTransactionalSUPPORTS();
    }

    /**
     * A方法调用B方法；如果A方法有事务，则B方法加入A方法的事务，如果A方法没有事务，
     * 则B方法抛出异常。MANDATORY是加在B方法上面，如果加在A方法上面直接报错
     */
    //@Transactional(propagation = Propagation.MANDATORY,rollbackFor = Exception.class)
    //@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void testMANDATORY(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("MANDATORY-A");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);

        serviceB.testTransactionalMANDATORY();
    }

    /**
     * A方法调用B方法；不管A方法有没有事务，B方法都新建一个自己的事务。
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void testREQUIRES_NEW() {
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("REQUIRES_NEW-A");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);

        serviceB.testTransactionalREQUIRES_NEW();
    }

    /**
     * A方法调用B方法；不管A方法有没有事务，B方法以非事务方式运行。
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void testNOT_SUPPORTED(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("NOT_SUPPORTED-A");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        serviceB.testTransactionalNOT_SUPPORTED();
        int i = 1/0;
    }

    /**
     * A方法调用B方法；如果A方法有事务，则B方法抛出异常，如果A方法没有事务，则B方法也以非事务方式运行。
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void testNEVER(){
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("NEVER-A");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        serviceB.testTransactionalNEVER();
    }

    /**
     * A方法调用B方法；如果A方法有事务，则B方法嵌套在A方法的事务中运行，如果A方法没有事务，则B方法自己新建一个事务。
     *
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void testNESTED() throws InterruptedException {
        CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
        cfgMetaDataDto.setCode("NESTED-A");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);

        serviceB.testTransactionalNESTED();
        Thread.sleep(15000);
        cfgMetaDataDto.setCode("NESTED-A1");
        configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
        //int i = 1/0;

    }
}
