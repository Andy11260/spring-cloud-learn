package com.zm.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * 服务提供者
 */
// @EnableDiscoveryClient 高度抽象，不关心具体服务发现与注册，支持多种服务发现组件
@EnableEurekaClient // 只能配合eureka使用
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
