package com.zm.cloud.contorller;


import com.zm.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("movie")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    // @PathVariable 作用是从URL中获取参数，这个参数对应上面URLMapping中通配的id
    public User findById(@PathVariable Long id) {
        return restTemplate.getForObject("http://192.168.1.172:8180/user/" + id, User.class);
    }

    @GetMapping("/findByName")
    public User findByName(@RequestParam String name) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("name", name);
        return restTemplate.getForObject("http://192.168.1.172:8180/user/findByName", User.class, uriVariables);
    }
}
