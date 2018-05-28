package com.zm.cloud.contorller;


import com.zm.cloud.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class MovieController {

    private static Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/{id}")
    // @PathVariable 作用是从URL中获取参数，这个参数对应上面URLMapping中通配的id
    public User findById(@PathVariable Long id) {
        return restTemplate.getForObject("http://provider-user/user/" + id, User.class);
    }

    @GetMapping("/findByName")
    public User findByName(@RequestParam String name) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("name", name);
        return restTemplate.getForObject("http://provider-user/user/findByName", User.class, uriVariables);
    }

    /**
     * 打印服务调用日志
     */
    @ResponseBody
    @GetMapping("/logger-instance")
    public ServiceInstance showInfo() {
        ServiceInstance instance = this.loadBalancerClient.choose("provider-user");
        logger.info("{}:{}:{}", instance.getServiceId(), instance.getHost(), instance.getPort());
        return instance;
    }
}
