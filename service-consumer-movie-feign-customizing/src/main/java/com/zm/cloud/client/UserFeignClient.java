package com.zm.cloud.client;

import com.zm.cloud.entity.User;
import com.zm.config.FeignConfig;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(name = "provider-user", configuration = FeignConfig.class) // configuration指定配置类
public interface UserFeignClient {

    @RequestLine("GET user/{id}")
    User findById(@Param("id") Long id);

    @RequestLine("GET user/findByName")
    User findByName(@Param("name") String name);

}
