package edu.gdpi.blogserver.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdpi.blogserver.api.ResponseCode;
import edu.gdpi.blogserver.api.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 匿名用户访问无权限资源时的处理器
 *
 * @author ZhengHaiFeng
 */
@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Resource
    public ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.warn("用户未认证");
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        String str = objectMapper.writeValueAsString(ResponseEntity.custom(ResponseCode.FAILURE_NO_AUTH, null));
        httpServletResponse.getOutputStream().write(str.getBytes(StandardCharsets.UTF_8));

    }
}
