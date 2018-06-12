package com.zm.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * 服务消费者
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MovieFeignManualApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieFeignManualApplication.class, args);
    }
}
