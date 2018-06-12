package com.zm.cloud.contorller;


import com.zm.cloud.client.UserFeignClient;
import com.zm.cloud.entity.User;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Import(FeignClientsConfiguration.class)
@RestController
@RequestMapping("movie")
public class MovieController {

    private UserFeignClient userFeignClient;
    private UserFeignClient adminFeignClient;

    @Autowired
    public MovieController(Decoder decoder, Encoder encoder,
                           Client client, Contract contract) {
        this.userFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user", "password1"))
                .target(UserFeignClient.class, "http://provider-user-auth/");

        this.adminFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin", "password2"))
                .target(UserFeignClient.class, "http://provider-user-auth/");
    }

    @GetMapping("/admin/{id}")
    // @PathVariable 作用是从URL中获取参数，这个参数对应上面URLMapping中通配的id
    public User findById(@PathVariable Long id) {
        return adminFeignClient.findById(id);
    }

    @GetMapping("/admin/findByName")
    public User findByName(@RequestParam String name) {
        return adminFeignClient.findByName(name);
    }

    @GetMapping("/user/{id}")
    // @PathVariable 作用是从URL中获取参数，这个参数对应上面URLMapping中通配的id
    public User findByIdUser(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }

    @GetMapping("/user/findByName")
    public User findByNameAdmin(@RequestParam String name) {
        return userFeignClient.findByName(name);
    }

}
