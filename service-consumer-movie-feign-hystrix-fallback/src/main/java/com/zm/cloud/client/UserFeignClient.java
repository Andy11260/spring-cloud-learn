package com.zm.cloud.client;

import com.zm.cloud.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider-user", fallback = UserFallback.class)
public interface UserFeignClient {

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);

    @GetMapping("user/findByName")
    User findByName(@RequestParam("name") String name);

}
