package com.halfsay.flower.interceptor;

import com.halfsay.flower.annotation.JWTIgnore;
import com.halfsay.flower.utils.JWTUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String jwtToken = request.getHeader(JWTUtils.AUTH_HEADER_KEY);
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(JWTIgnore.class)) {
            JWTIgnore jwtIgnore = method.getAnnotation(JWTIgnore.class);
            if (jwtIgnore.value()) {
                return true;
            }
        }
        if (JWTUtils.checkToken(jwtToken)) {
            return true;
        } else {
            return false;
        }
    }
}
