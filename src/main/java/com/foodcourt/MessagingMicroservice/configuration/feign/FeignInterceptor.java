package com.foodcourt.MessagingMicroservice.configuration.feign;

import com.foodcourt.MessagingMicroservice.configuration.security.jwt.JwtConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate){
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String jwt = request.getHeader(JwtConstants.JWT_HEADER);
        requestTemplate.header(JwtConstants.JWT_HEADER, jwt);
    }
}
