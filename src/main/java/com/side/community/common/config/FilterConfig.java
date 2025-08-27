package com.side.community.common.config;

import com.side.community.common.filter.JwtAuthenticationFilter;
import com.side.community.common.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final JwtProvider jwtProvider;

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilter() {
        FilterRegistrationBean<JwtAuthenticationFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new JwtAuthenticationFilter(jwtProvider));
        bean.addUrlPatterns("/*");
        return bean;
    }
}