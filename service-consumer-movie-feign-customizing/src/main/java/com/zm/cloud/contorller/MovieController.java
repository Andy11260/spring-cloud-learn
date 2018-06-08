package com.zm.cloud.contorller;


import com.zm.cloud.entity.User;
import com.zm.cloud.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/{id}")
    // @PathVariable 作用是从URL中获取参数，这个参数对应上面URLMapping中通配的id
    public User findById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }

    @GetMapping("/findByName")
    public User findByName(@RequestParam String name) {
        return userFeignClient.findByName(name);
    }
}
