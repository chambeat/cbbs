package com.chm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableTransactionManagement
@MapperScan("com.chm.mapper")
@SpringBootApplication
public class CbbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CbbsApplication.class, args);
    }

}
