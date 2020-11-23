package edu.gdpi.blogserver.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.gdpi.blogserver.api.ResponseCode;
import edu.gdpi.blogserver.api.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * 权限不足处理器
 * 用来解决认证过的用户访问无权限资源时的异常
 *
 * @author ZhengHaiFeng
 */
@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        log.warn("权限不足");
        httpServletResponse.setContentType("application/json; charset=UTF-8");

        String uri = httpServletRequest.getRequestURI();
        HashMap<String, Object> map = new HashMap<>();
        map.put("url", uri);
        String str = objectMapper.writeValueAsString(ResponseEntity.custom(ResponseCode.FAILURE_ACCESS_DENIED, map));
        httpServletResponse.getOutputStream().write(str.getBytes(StandardCharsets.UTF_8));
    }
}
