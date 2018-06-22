package com.zm.cloud.client;

import com.zm.cloud.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UserFallback implements UserFeignClient {

    @Override
    public User findById(Long id) {
        return getDefault();
    }

    @Override
    public User findByName(String name) {
        return getDefault();
    }

    private User getDefault() {
        return new User(-1L, "default", "default", 0, new BigDecimal("0"));
    }
}
