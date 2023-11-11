package com.gooroomee.gooroomeeadapter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gooroomee.gooroomeeadapter.controller.GrmAdapterController;
import com.gooroomee.gooroomeeadapter.interceptor.ApiLoggingInterceptor;
import com.gooroomee.gooroomeeadapter.interceptor.AuthInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Value(value = "${api.auth.enabled}")
	private String apiAuthEnabled;
	
	@Autowired
	ApiLoggingInterceptor apiLoggingInterceptor;
	
	@Autowired
	AuthInterceptor authInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry
			.addInterceptor(apiLoggingInterceptor)
				.order(2)
				.addPathPatterns(GrmAdapterController.API_URL_TOKEN + "/**")
				.excludePathPatterns("/js/**");
		
		Boolean isApiAuthEnabled = Boolean.valueOf(apiAuthEnabled);
		if(isApiAuthEnabled) {
			registry
				.addInterceptor(authInterceptor)
					.order(1)
					.addPathPatterns("/intrf/**");
		}
	}
}
