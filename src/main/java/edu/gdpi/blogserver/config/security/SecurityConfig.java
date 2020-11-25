package edu.gdpi.blogserver.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdpi.blogserver.config.security.handler.CustomAccessDeniedHandler;
import edu.gdpi.blogserver.config.security.handler.CustomAuthenticationEntryPoint;
import edu.gdpi.blogserver.config.security.handler.CustomAuthenticationFailureHandler;
import edu.gdpi.blogserver.config.security.handler.CustomAuthenticationSuccessHandler;
import edu.gdpi.blogserver.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Arrays;

/**
 * @author ZhengHaiFeng
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private CustomAccessDeniedHandler accessDeniedHandler;
    @Resource
    private CustomAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private CustomAuthenticationFailureHandler failureHandler;
    @Resource
    private CustomAuthenticationSuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers("/api/admin/**").hasRole("admin")
                .antMatchers("/api/guest/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginProcessingUrl("/api/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                // 关闭 Session
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf()
                .disable(); // CSRF跨站请求伪造关闭
        http.addFilterBefore(new JwtAuthenticationFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setMaxAge(Duration.ofHours(1));
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }
}
