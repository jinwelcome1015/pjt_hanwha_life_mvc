package com.gooroomee.gooroomeeadapter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gooroomee.gooroomeeadapter.exception.AuthException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

	/** api auth enabled */
	@Value(value = "#{propertiesFactoryBean['api.auth.enabled']}")
	private String apiAuthEnabled;

	/** api auth token */
	@Value(value = "#{propertiesFactoryBean['api.auth.key']}")
	private String apiAuthKey;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		log.debug("***" + methodName);

		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		log.debug("***" + methodName);

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (Boolean.valueOf(apiAuthEnabled)) {
			String xApiKey = request.getHeader("X-API-Key");
			if (!xApiKey.equals(apiAuthKey)) {
				log.debug("xApiKey : " + xApiKey);
				throw new AuthException("X-API-Key 헤더 인증 에러");
			}
		}

		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		log.debug("***" + methodName);

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
