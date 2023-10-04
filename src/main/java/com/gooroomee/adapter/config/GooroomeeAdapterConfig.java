package com.gooroomee.adapter.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class GooroomeeAdapterConfig {
	
	private static final String PROPERTIES_PATH = "/properties/";
	private static final String PROPERTIES_EXTENSION = ".properties";

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
}
