package com.zm.springboot.autoconfig.starter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @RequestMapping("test")
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("aaa", "test auto config");
        map.put("bbb", "bbb");
        map.put("ccc", 123);
        return map;
    }
}
