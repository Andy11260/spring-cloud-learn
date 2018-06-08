package com.zm.config;


import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 自定义配置类
 */
@Configuration
public class FeignConfig {

    /**
     * 将契约改为feign的默认契约
     * @return 默认的feign契约
     */
    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }
}
