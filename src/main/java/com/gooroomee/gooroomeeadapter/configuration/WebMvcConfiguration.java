package com.gooroomee.gooroomeeadapter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gooroomee.gooroomeeadapter.interceptor.AuthInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
	
	@Autowired
	AuthInterceptor authInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor).addPathPatterns("/**");
	}
}
