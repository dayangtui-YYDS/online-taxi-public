package com.ycc.apipassenger.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: InterceptorConfig
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-11-26 22:03
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    public JwtInterceptor getJwtInterceptor(){
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getJwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/noAuthTest")
                .excludePathPatterns("/verification-code")
                .excludePathPatterns("/verification-code-check");
    }
}