package com.zm.springboot.autoconfig.starter;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TestController.class})
public class TestAutoConfigure {

}
