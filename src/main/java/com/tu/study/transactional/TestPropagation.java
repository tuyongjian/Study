package com.tu.study.transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author tuyongjian
 * @date 2022/11/22 14:58
 */
@SpringBootTest
public class TestPropagation {


    @Resource
    private ServicePropagation servicePropagation;

    @Test
    public void testREQUIRED() throws Exception {
        servicePropagation.testREQUIRED();
    }

    @Test
    public void testSUPPORTS() throws Exception {
        servicePropagation.testSUPPORTS();
    }

    @Test
    public void testMANDATORY() throws Exception {
        servicePropagation.testMANDATORY();
    }

    @Test
    public void testREQUIRES_NEW() throws Exception {
        servicePropagation.testREQUIRES_NEW();
    }

    @Test
    public void testNOT_SUPPORTED() throws Exception {
        servicePropagation.testNOT_SUPPORTED();
    }

    @Test
    public void testNEVER() throws Exception {
        servicePropagation.testNEVER();
    }
    @Test
    public void testNESTED() throws Exception {
        servicePropagation.testNESTED();
    }
}
