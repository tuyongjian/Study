package com.tu.study.esmapper;

import com.tu.study.dto.EsStudentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

/**
 * @author tuyongjian
 * @date 2022/11/17 14:56
 */
@SpringBootTest
@Slf4j
public class Test {

    @Resource
    private EsStudentMapper esStudentMapper;

    @org.junit.jupiter.api.Test
    public void testSave(){
        EsStudentDto esStudentDto = EsStudentDto.builder()
                .sId("1")
                .sName("test")
                .sAge(30)
                .sAddress("anhui")
                .sCourseList(new String[]{"语文", "数学"})
                .sColorList(Arrays.asList("yellow", "red"))
                .sCreateTime(new Date())
                .build();

        EsStudentDto save = esStudentMapper.save(esStudentDto);
        log.info("es save result---{}",save.toString());

    }

    @org.junit.jupiter.api.Test
    public void testQuery(){
        Optional<EsStudentDto> byId = esStudentMapper.findById("1");
        log.info("es query result---{}",byId.get().toString());

    }
}
