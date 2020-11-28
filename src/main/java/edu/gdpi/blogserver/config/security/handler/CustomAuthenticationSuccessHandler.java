package edu.gdpi.blogserver.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdpi.blogserver.api.ResponseEntity;
import edu.gdpi.blogserver.entity.JwtUser;
import edu.gdpi.blogserver.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhengHaiFeng
 */
@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private ObjectMapper ob;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        JwtUser userDetails = (JwtUser) authentication.getPrincipal();

        log.info("用户： {} 在 IP： {} 处登录，该用户拥有 {} 权限", userDetails.getUsername(), details.getRemoteAddress(), userDetails.getAuthorities());

        Map<String, Object> map = new HashMap<>();
        map.put("token", JwtUtils.createToken(userDetails.getUsername()));
        map.put("username", userDetails.getUsername());
        map.put("roles", userDetails.getAuthorities());
        map.put("id", userDetails.getId());
        String msg = ob.writeValueAsString(ResponseEntity.success(map));
        httpServletResponse.getOutputStream().write(msg.getBytes(StandardCharsets.UTF_8));
    }
}
