package com.zm.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon的配置类
 * 注意:该类不应该在主应用程序上下文的@ComponentScan 中
 *      即不应在spring-boot启动类所在的包和子包中。
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule () {
        return new RandomRule();
    }
}
