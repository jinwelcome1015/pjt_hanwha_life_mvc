package com.gooroomee.backbone.external.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gooroomee.backbone.external.controller.GrmExternalBackboneController;
import com.gooroomee.backbone.external.interceptor.auth.AuthInterceptor;
import com.gooroomee.backbone.external.interceptor.logging.server.ApiServerLoggingInterceptor;

/**
 * 인터셉터 등록을 위한 Configuration 클래스
 * @author 신용진
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	/** api auth 활성화 여부 */
	@Value(value = "${api.auth.enabled}")
	private String apiAuthEnabled;

	/** 이 어플리케이션이 서버가 되어 클라이언트에 요청에 대한 응답을 할때, 요청데이터와 응답데이터를 기록하기 위한 logging용 인터셉터 객체 */
	@Autowired
	ApiServerLoggingInterceptor apiLoggingInterceptor;

	/** API 접근에 대한 인증, 인가 로직을 담당하는 AuthInterceptor 객체 */
	@Autowired
	AuthInterceptor authInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

//		registry.addInterceptor(apiLoggingInterceptor).order(2).addPathPatterns(GrmAdapterController.API_URL_TOKEN + "/**", "/counselling/otp").excludePathPatterns("/js/**");
		
		registry.addInterceptor(apiLoggingInterceptor).order(2).addPathPatterns(GrmExternalBackboneController.API_URL_TOKEN + "/**", "/counselling/otp").excludePathPatterns("/js/**", GrmExternalBackboneController.API_URL_TOKEN + 	"/rgstrImgSys");

		Boolean isApiAuthEnabled = Boolean.valueOf(apiAuthEnabled);
		if (isApiAuthEnabled) {
			registry.addInterceptor(authInterceptor).order(1).addPathPatterns("/intrf/**");
		}
	}
}
