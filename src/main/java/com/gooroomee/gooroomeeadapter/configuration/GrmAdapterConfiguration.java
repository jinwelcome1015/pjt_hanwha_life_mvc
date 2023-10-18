package com.gooroomee.gooroomeeadapter.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class GrmAdapterConfiguration {

	private static final String PROPERTIES_PATH = "/properties/";
	private static final String PROPERTIES_EXTENSION = ".properties";
	
	/*
	private static final int READ_TIMEOUT_SECOND = 5;
	private static final int CONNECTION_TIMEOUT_SECOND = 5;
	*/
    
	@Value(value = "${spring.profiles.active}")
	private String springProfilesActive;

	@Bean(name = "propertiesFactoryBean")
	public PropertiesFactoryBean propertiesFactoryBean() {

		String propertiesFile = PROPERTIES_PATH + springProfilesActive + PROPERTIES_EXTENSION;
		ClassPathResource classPathResource = new ClassPathResource(propertiesFile);

		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(classPathResource);

		return propertiesFactoryBean;
	}

	/*
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.setConnectTimeout(Duration.ofSeconds(CONNECTION_TIMEOUT_SECOND))
				.setReadTimeout(Duration.ofSeconds(READ_TIMEOUT_SECOND))
				.build();
	}
	*/
}
