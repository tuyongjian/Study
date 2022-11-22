package com.tu.study.transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author tuyongjian
 * @date 2022/11/22 10:52
 */
@SpringBootTest
public class TestTrans {

    @Resource
    private ServiceLose serviceLose;


    /**
     * 事务生效
     */
    @Test
    public void testLoseA() throws Exception {
        serviceLose.testTransactionalA();
    }

    /**
     * 事务不生效，没有public修饰
     */
    @Test
    public void testLoseB() throws Exception {
        serviceLose.testTransactionalB();
    }

    /**
     * 事务不生效，捕捉异常
     */
    @Test
    public void testLoseC() throws Exception {
        serviceLose.testTransactionalC();
    }
    /**
     * 事务不生效，调用同类的方法
     */
    @Test
    public void testLoseD() throws Exception {
        serviceLose.testTransactionalD();
    }

    /**
     * 事务不生效，传播机制修改
     */
    @Test
    public void testLoseE() throws Exception {
        serviceLose.testTransactionalE();
    }




}
