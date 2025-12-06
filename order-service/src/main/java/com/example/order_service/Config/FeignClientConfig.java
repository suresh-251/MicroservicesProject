package com.example.order_service.Config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    private final String jwtToken =" eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3NjQ4NTQ2MjUsImV4cCI6MTc2NDg1ODIyNX0.KJawKECHKpvW3MMGY3Vx_TDPA2hT6P081G_TK7fGNvs"; // For testing, or fetch dynamically

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {

            requestTemplate.header("Authorization", "Bearer " + jwtToken);
        };
    }
}
