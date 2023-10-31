package com.gooroomee.gooroomeeadapter.configuration;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc003ReqDto;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_I;
import com.gooroomee.gooroomeeadapter.interceptor.RestTemplateLoggingRequestInterceptor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class GrmAdapterConfiguration {
	/*
	private static final String PROPERTIES_PATH = "/properties/";
	private static final String PROPERTIES_EXTENSION = ".properties";
	*/
	private static final int READ_TIMEOUT_SECOND = 5;
	private static final int CONNECTION_TIMEOUT_SECOND = 3;
	private static final int MAXIMUM_TOTAL_CONNECTION = 50;
	private static final int MAXIMUM_CONNECTION_PER_ROUTE = 20;

	
	
	@Autowired
	RestTemplateLoggingRequestInterceptor restTemplateLoggingRequestInterceptor;

	@Value(value = "${spring.profiles.active}")
	private String springProfilesActive;

	/*
	@Bean(name = "propertiesFactoryBean")
	public PropertiesFactoryBean propertiesFactoryBean() {
	
		String propertiesFile = PROPERTIES_PATH + springProfilesActive + PROPERTIES_EXTENSION;
		ClassPathResource classPathResource = new ClassPathResource(propertiesFile);
	
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(classPathResource);
	
		return propertiesFactoryBean;
	}
	*/
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldAccessLevel(AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true)
//				.setMatchingStrategy(MatchingStrategies.LOOSE);
				.setMatchingStrategy(MatchingStrategies.STRICT);

		return modelMapper;
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
				.registerModule(new SimpleModule())
				;
		return objectMapper;
	}
		
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {

		HttpClient httpClient = HttpClientBuilder.create()
				.setMaxConnTotal(MAXIMUM_TOTAL_CONNECTION)
				.setMaxConnPerRoute(MAXIMUM_CONNECTION_PER_ROUTE)
				.build();

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setHttpClient(httpClient);

		RestTemplate restTemplate = restTemplateBuilder
				.setReadTimeout(Duration.ofSeconds(READ_TIMEOUT_SECOND))
				.setConnectTimeout(Duration.ofSeconds(CONNECTION_TIMEOUT_SECOND))
				.additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
				.additionalInterceptors(restTemplateLoggingRequestInterceptor)
				.build();

		if (log.isDebugEnabled()) {
			ClientHttpRequestFactory clientHttpRequestFactory = new BufferingClientHttpRequestFactory(
					new SimpleClientHttpRequestFactory());
			restTemplate.setRequestFactory(clientHttpRequestFactory);
			return restTemplate;
		}
		return restTemplate;
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
