package com.sushildangi.exception;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sushildangi.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
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
