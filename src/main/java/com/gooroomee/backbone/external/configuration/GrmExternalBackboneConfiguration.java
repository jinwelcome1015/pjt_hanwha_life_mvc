package com.gooroomee.backbone.external.configuration;

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
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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
import com.gooroomee.backbone.external.controller.GrmExternalBackboneController;
import com.gooroomee.backbone.external.dto.client.Mvc003ReqDto;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs003_I;
import com.gooroomee.backbone.external.filter.CustomServletWrappingFilter;
import com.gooroomee.backbone.external.interceptor.logging.client.IfConsumerLoggingInterceptor;
import com.gooroomee.backbone.external.interceptor.logging.client.IfProviderLoggingInterceptor;
import com.gooroomee.backbone.external.interceptor.logging.client.ImageSystemLoggingInterceptor;

import lombok.extern.slf4j.Slf4j;


/**
 * 스프링 컨테이너 설정을 위한 Configuration 클래스
 * @author 신용진
 */
@Configuration
@Slf4j
public class GrmExternalBackboneConfiguration {

	/** 서버로부터 응답을 받는 데 소요되는 시간 제한 */
	private static final int READ_TIMEOUT_SECOND = 5;
	
	/** 서버에 연결하는 데 소요되는 시간 제한 */
	private static final int CONNECTION_TIMEOUT_SECOND = 3;
	
	/** 한 번에 만들 수 있는 최대 HTTP 연결 수 */
	private static final int MAXIMUM_TOTAL_CONNECTION = 50;
	
	/** 호스트 또는 경로 당 최대 HTTP 연결 수 */
	private static final int MAXIMUM_CONNECTION_PER_ROUTE = 20;
	
	/** 이 어플리케이션이 인터페이스 provider 가 되어, 구루미 코어와 통신할때 요청데이터와 응답데이터를 기록하기 위한 logging용 IfProviderLoggingInterceptor 객체*/
	@Autowired
	IfProviderLoggingInterceptor ifProviderLoggingInterceptor;

	/** 이 어플리케이션이 인터페이스 provider 가 되어, 구루미 코어와 통신할때 요청데이터와 응답데이터를 기록하기 위한 logging용 IfConsumerLoggingInterceptor 객체*/
	@Autowired
	IfConsumerLoggingInterceptor ifConsumerLoggingInterceptor;
	
	/** 이 어플리케이션이 이미지 시스템 서버에 이미지 등록 요청을 할때 요청데이터와 응답데이터를 기록하기 위한 logging용 ImageSystemLoggingInterceptor 객체 */
	@Autowired
	ImageSystemLoggingInterceptor imageSystemLoggingInterceptor;

	
	/**
	 * FilterRegistrationBean 객체를 생성한후, 그것에 필터를 등록하고 그 FilterRegistrationBean 객체를 스프링 컨테이너에 추가한다.  
	 * @return 필터를 등록한 FilterRegistrationBean 객체
	 */
	@Bean
	public FilterRegistrationBean<CustomServletWrappingFilter> filterRegistrationBean() {
		FilterRegistrationBean<CustomServletWrappingFilter> filterRegistrationBean = new FilterRegistrationBean<>(new CustomServletWrappingFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}

	
	/**
	 * ModelMapper 객체를 스프링 IoC 컨테이너에 추가한다.
	 * @return ModelMapper 객체
	 */
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);

		return modelMapper;
	}

	/**
	 * ObjectMapper 객체를 스프링 IoC 컨테이너에 추가한다.
	 * @return ObjectMapper 객체
	 */
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).registerModule(new SimpleModule());
		return objectMapper;
	}

	
	/**
	 * 이 어플리케이션이 인터페이스 consumer 로서, 인터페이스 엔드포인트와 통신하기 위한 RestTemplate 객체를 스프링  컨테이너에 추가한다.
	 * @param restTemplateBuilder RestTemplateBuilder 객체
	 * @return RestTemplate 객체
	 */
	@Bean(name = {"restTemplateForIfConsumer"})
	public RestTemplate restTemplateForIfConsumer(RestTemplateBuilder restTemplateBuilder) {

		HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(MAXIMUM_TOTAL_CONNECTION).setMaxConnPerRoute(MAXIMUM_CONNECTION_PER_ROUTE).build();

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setHttpClient(httpClient);

		RestTemplate restTemplate = restTemplateBuilder
				.setReadTimeout(Duration.ofSeconds(READ_TIMEOUT_SECOND))
				.setConnectTimeout(Duration.ofSeconds(CONNECTION_TIMEOUT_SECOND))
				.additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
				.additionalInterceptors(ifConsumerLoggingInterceptor)
				.build();

		if (log.isDebugEnabled()) {
			ClientHttpRequestFactory clientHttpRequestFactory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
			restTemplate.setRequestFactory(clientHttpRequestFactory);
			return restTemplate;
		}
		return restTemplate;
	}
	
	
	/**
	 * 이 어플리케이션이 인터페이스 provider 로서, 구르미 코어 서버와 통신하기 위한 RestTemplate 객체를 스프링  컨테이너에 추가한다.
	 * @param restTemplateBuilder RestTemplateBuilder 객체
	 * @return RestTemplate 객체
	 */
	@Bean(name = {"restTemplateForIfProvider"})
	public RestTemplate restTemplateForIfProvider(RestTemplateBuilder restTemplateBuilder) {

		HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(MAXIMUM_TOTAL_CONNECTION).setMaxConnPerRoute(MAXIMUM_CONNECTION_PER_ROUTE).build();

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setHttpClient(httpClient);

		RestTemplate restTemplate = restTemplateBuilder
				.setReadTimeout(Duration.ofSeconds(READ_TIMEOUT_SECOND))
				.setConnectTimeout(Duration.ofSeconds(CONNECTION_TIMEOUT_SECOND))
				.additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
				.additionalInterceptors(ifProviderLoggingInterceptor)
				.build();

		if (log.isDebugEnabled()) {
			ClientHttpRequestFactory clientHttpRequestFactory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
			restTemplate.setRequestFactory(clientHttpRequestFactory);
			return restTemplate;
		}
		return restTemplate;
	}
	
	
	/**
	 * 이미지시스템 서버에 이미지 등록 요청을 하기 위한  RestTemplate 객체를 스프링  컨테이너에 추가한다.
	 * @param restTemplateBuilder RestTemplateBuilder 객체
	 * @return RestTemplate 객체
	 */
	@Bean(name = {"restTemplateForImageSystem"})
	public RestTemplate restTemplateForImageSystem(RestTemplateBuilder restTemplateBuilder) {

		HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(MAXIMUM_TOTAL_CONNECTION).setMaxConnPerRoute(MAXIMUM_CONNECTION_PER_ROUTE).build();

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setHttpClient(httpClient);

		RestTemplate restTemplate = restTemplateBuilder
				.setReadTimeout(Duration.ofSeconds(READ_TIMEOUT_SECOND))
				.setConnectTimeout(Duration.ofSeconds(CONNECTION_TIMEOUT_SECOND))
				.additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
				.additionalInterceptors(imageSystemLoggingInterceptor)
				.build();

		if (log.isDebugEnabled()) {
			ClientHttpRequestFactory clientHttpRequestFactory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
			restTemplate.setRequestFactory(clientHttpRequestFactory);
			return restTemplate;
		}
		return restTemplate;
	}
}
