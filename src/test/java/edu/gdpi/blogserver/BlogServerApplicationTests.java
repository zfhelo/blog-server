package edu.gdpi.blogserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@SpringBootTest
class BlogServerApplicationTests {

    @Resource
    private UserDetailsService userDetailsService;

    @Test
    void contextLoads() {
        userDetailsService.loadUserByUsername("guest");
    }

}
