package com.zm.cloud.contorller;


import com.zm.cloud.dao.UserRepository;
import com.zm.cloud.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    // @PathVariable 作用是从URL中获取参数，这个参数对应上面URLMapping中通配的id
    public User findById(@PathVariable Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails user = (UserDetails) principal;
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                logger.info("username=[{}], password=[{}], role=[{}]", user.getUsername(), user.getPassword(), authority.getAuthority());
            }
        }

        return userRepository.findOne(id);
    }

    @GetMapping("/findByName")
    public User findByName(@RequestParam String name) {
        User u = new User();
        u.setName(name);

        Example<User> example = Example.of(u);
        return userRepository.findOne(example);
    }
}
