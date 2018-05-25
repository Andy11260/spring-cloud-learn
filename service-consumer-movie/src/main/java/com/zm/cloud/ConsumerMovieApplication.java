package com.zm.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * 服务消费者
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerMovieApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
