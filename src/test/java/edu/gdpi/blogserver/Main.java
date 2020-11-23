package edu.gdpi.blogserver;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ZhengHaiFeng
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("1"));
    }
}
