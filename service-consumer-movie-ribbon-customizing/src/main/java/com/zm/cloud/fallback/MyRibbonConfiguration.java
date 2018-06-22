package com.zm.cloud.fallback;

import com.zm.config.RibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * name指定ribbon客户端
 * configuration指定ribbon配置类
 */
@Configuration
@RibbonClient(name = "provider-user", configuration = RibbonConfiguration.class)
public class MyRibbonConfiguration {
}
