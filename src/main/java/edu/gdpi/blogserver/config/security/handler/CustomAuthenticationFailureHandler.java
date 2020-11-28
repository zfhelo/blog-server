package edu.gdpi.blogserver.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdpi.blogserver.api.ResponseCode;
import edu.gdpi.blogserver.api.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 认证失败处理器
 *
 * @author ZhengHaiFeng
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Resource
    private ObjectMapper ob;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String msg = ob.writeValueAsString(ResponseEntity.custom(ResponseCode.FAILURE_NO_AUTH, null));
        httpServletResponse.getOutputStream().write(msg.getBytes(StandardCharsets.UTF_8));
    }
}
