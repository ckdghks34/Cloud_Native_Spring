package com.example.ecomerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/** 
 * Spring boot가 실행이 되면 
 * 아래의 @SpringBootApplication 어노테이션을 찾아 실행해준다.
 * main() 함수라고 생각하면 됨.
 */

/**
 * Eureka Server의 역할을 하기 위해선 등록해야한다.
 * @EnableEurekaServer 어노테이션을 달아야함.
 */
@SpringBootApplication
@EnableEurekaServer
public class SpringCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudApplication.class, args);
    }

}
