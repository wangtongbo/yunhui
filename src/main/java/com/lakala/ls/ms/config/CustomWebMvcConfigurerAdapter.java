package com.lakala.ls.ms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter  {
	
	
	@Bean
    public UserInterceptor localeInterceptor() {
        return new UserInterceptor();
    }
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor()).addPathPatterns("/**");  //对来自/user/** 这个链接来的请求进行拦截
    }

}

