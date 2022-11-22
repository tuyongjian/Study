package com.tu.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan("com.tu.study.dao")
@EnableTransactionManagement
public class Application
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class,args);
    }
}
