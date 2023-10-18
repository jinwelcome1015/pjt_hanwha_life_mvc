package com.gooroomee.gooroomeeadapter.configuration;

import java.time.Duration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc003ReqDto;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_I;

@Configuration
public class GrmAdapterConfiguration {

	private static final String PROPERTIES_PATH = "/properties/";
	private static final String PROPERTIES_EXTENSION = ".properties";

	private static final int READ_TIMEOUT_SECOND = 5;
	private static final int CONNECTION_TIMEOUT_SECOND = 5;

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

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true)
				.setMatchingStrategy(MatchingStrategies.LOOSE);

		return modelMapper;
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
	/*
		@Bean
		public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
	
			// Apache HttpComponents
			HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(50) // 최대 커넥션 수
					.setMaxConnPerRoute(20).build(); // 각 호스트(IP와 Port 의 조합)당 커넥션 풀에 생성가능한 커넥션 수
	
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setHttpClient(httpClient);
	
			return restTemplateBuilder
					// 로깅 인터셉터에서 Stream 소비 하기 때문에 BufferingClientHttpRequestFactory 사용
					.requestFactory(() -> new BufferingClientHttpRequestFactory(factory))
					.setReadTimeout(Duration.ofSeconds(5)) // read timeout
					.setConnectTimeout(Duration.ofSeconds(3)) // connection timeout
					.additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8)) // 메시지 컨버터 추가
					.additionalInterceptors(restTemplateLoggingRequestInterceptor).build();
		}
		*/
}
