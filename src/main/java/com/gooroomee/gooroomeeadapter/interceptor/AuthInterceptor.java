package com.gooroomee.gooroomeeadapter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gooroomee.gooroomeeadapter.controller.GrmAdapterController;
import com.gooroomee.gooroomeeadapter.exception.AuthException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

	/** api auth token */
	@Value(value = "${api.auth.key}")
	private String apiAuthKey;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String xApiKey = StringUtils.defaultString(request.getHeader("X-API-Key"));
		if (!xApiKey.equals(apiAuthKey)) {
			request.setAttribute(GrmAdapterController.EXCEPTION_ATTRIBUTE_NAME,
					new AuthException("X-API-Key 헤더 인증 에러"));
			request.getRequestDispatcher(GrmAdapterController.EXCEPTION_CONTROLLER_PATH).forward(request, response);
			return false;
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
