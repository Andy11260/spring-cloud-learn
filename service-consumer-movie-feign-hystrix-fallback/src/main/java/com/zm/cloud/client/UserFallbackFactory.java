package com.zm.cloud.client;

import com.zm.cloud.entity.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Component
public class UserFallbackFactory implements FallbackFactory<UserFeignClientFactory> {

    private static final Logger logger = LoggerFactory.getLogger(UserFallbackFactory.class);

    @Override
    public UserFeignClientFactory create(final Throwable cause) {
        return new UserFeignClientFactory() {
            @Override
            public User findById(Long id) {
                logger.error("Error:", cause);
                return getDefault();
            }

            @Override
            public User findByName(String name) {
                logger.error("Error:", cause);
                return getDefault();
            }
        };
    }

    private User getDefault() {
        return new User(-1L, "default", "default", 0, new BigDecimal("0"));
    }
}
