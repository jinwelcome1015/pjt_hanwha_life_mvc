package com.gooroomee.gooroomeeadapter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor{
	
	/** api auth token */
	@Value(value = "#{propertiesFactoryBean['api.auth.token']}")
	private String apiAuthToken;


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		log.debug("***" + methodName);
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		log.debug("***" + methodName);
		
		System.out.println("### " + apiAuthToken);
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		log.debug("***" + methodName);
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
