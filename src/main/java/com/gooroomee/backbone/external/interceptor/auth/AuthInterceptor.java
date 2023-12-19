package com.gooroomee.backbone.external.interceptor.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gooroomee.backbone.external.controller.GrmExternalBackboneController;
import com.gooroomee.backbone.external.exception.AuthException;

import lombok.extern.slf4j.Slf4j;

/**
 * API 접근에 대한 인증, 인가 로직을 담당하는 클래스
 * @author 신용진
 */
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

	/** api auth token */
	@Value(value = "${api.auth.key}")
	private String apiAuthKey;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String xApiKey = StringUtils.defaultString(request.getHeader("X-API-Key"));
		if (!xApiKey.equals(apiAuthKey)) {
			request.setAttribute(GrmExternalBackboneController.EXCEPTION_ATTRIBUTE_NAME, new AuthException("X-API-Key 헤더 인증 에러"));
			request.getRequestDispatcher(GrmExternalBackboneController.EXCEPTION_CONTROLLER_PATH).forward(request, response);
			return false;
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
