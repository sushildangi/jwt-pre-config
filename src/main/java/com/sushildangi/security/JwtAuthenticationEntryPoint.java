package com.sushildangi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sushildangi.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
        // httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setTimestamp(Instant.now().toString());
        apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        apiResponse.setError(HttpStatus.UNAUTHORIZED.name());
        apiResponse.setMessage(e.getMessage());
        apiResponse.setSuccess(false);
        apiResponse.setPath(request.getRequestURI().substring(request.getContextPath().length()));
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(mapper.writeValueAsString(apiResponse));
    }
}
