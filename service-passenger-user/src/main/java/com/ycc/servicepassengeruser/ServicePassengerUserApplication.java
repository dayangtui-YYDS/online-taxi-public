package com.ycc.servicepassengeruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: ServicePassengerUser
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-24 11:24
 */
@SpringBootApplication
@MapperScan("com.ycc.servicepassengeruser.mapper")
public class ServicePassengerUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePassengerUserApplication.class);
    }
}