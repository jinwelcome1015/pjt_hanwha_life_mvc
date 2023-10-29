package com.gooroomee.gooroomeeadapter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gooroomee.gooroomeeadapter.interceptor.AuthInterceptor;
import com.gooroomee.gooroomeeadapter.interceptor.MockDataInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	/** api auth enabled */
//	@Value(value = "#{propertiesFactoryBean['api.auth.enabled']}")
	@Value(value = "${api.auth.enabled}")
	private String apiAuthEnabled;

	@Autowired
	MockDataInterceptor mockDataInterceptor;
	
	@Autowired
	AuthInterceptor authInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(mockDataInterceptor).addPathPatterns("/intrf/**");
		
		Boolean isApiAuthEnabled = Boolean.valueOf(apiAuthEnabled);
		if(isApiAuthEnabled) {
			registry
				.addInterceptor(authInterceptor)
					.addPathPatterns("/intrf/**");
		}
	}
}
