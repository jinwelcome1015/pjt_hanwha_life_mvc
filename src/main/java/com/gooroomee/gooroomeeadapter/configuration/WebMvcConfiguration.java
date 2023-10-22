package com.gooroomee.gooroomeeadapter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gooroomee.gooroomeeadapter.interceptor.AuthInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	/** api auth enabled */
	@Value(value = "#{propertiesFactoryBean['api.auth.enabled']}")
	private String apiAuthEnabled;

	@Autowired
	AuthInterceptor authInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		Boolean isApiAuthEnabled = Boolean.valueOf(apiAuthEnabled);
		if(isApiAuthEnabled) {
			registry
				.addInterceptor(authInterceptor)
					.addPathPatterns("/intrf/**");
		}
	}
}
