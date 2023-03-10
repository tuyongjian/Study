package com.tu.study.transactional;

import com.tu.study.dao.CfgMetaDataDTO;
import com.tu.study.dao.ConfigMetaDataDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * @author tuyongjian
 * @date 2022/11/22 10:52
 */
@SpringBootTest
public class TestTransactionTemplate {


    @Autowired
    private TransactionTemplate transactionTemplate;

    @Resource
    private ConfigMetaDataDao configMetaDataDao;

    @Test
    public void testTransactionTemplate() throws Exception {
        final Integer count = transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus transactionStatus) {
                CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
                cfgMetaDataDto.setCode("TransactionTemplate");
                int result = configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
                //int i = 1/0;
                //transactionStatus.setRollbackOnly();
                return result;
            }
        });
        System.out.println("-----------------"+count);
    }


    @Test
    public void testTransactionTemplateNoResult() throws Exception {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                CfgMetaDataDTO cfgMetaDataDto = new CfgMetaDataDTO();
                cfgMetaDataDto.setCode("TransactionTemplateNosult");
                configMetaDataDao.addConfigMetaData(cfgMetaDataDto);
                //int i = 1/0;
                //transactionStatus.setRollbackOnly();
            }
        });
    }







}
