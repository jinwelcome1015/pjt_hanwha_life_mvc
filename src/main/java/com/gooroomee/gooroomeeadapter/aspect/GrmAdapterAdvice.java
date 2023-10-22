package com.gooroomee.gooroomeeadapter.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gooroomee.gooroomeeadapter.controller.GrmAdapterController;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class GrmAdapterAdvice {
	
	@Around("execution(* com.gooroomee.gooroomeeadapter.controller.*.*(..))")
    public Object responseWithMockData(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		Class<? extends Object> targetClass = proceedingJoinPoint.getTarget().getClass();
		String targetMethodName = proceedingJoinPoint.getSignature().getName();
		Method[] targetClassMethods = targetClass.getMethods();
		for (Method targetClassMethod : targetClassMethods) {
			if(targetClassMethod.getName().equals(targetMethodName)) {
				RequestMapping requestMapping = targetClassMethod.getAnnotation(RequestMapping.class);
				if(requestMapping != null) {
					String[] paths = requestMapping.path();
					for (String path : paths) {
						if(path.startsWith(GrmAdapterController.API_URL_TOKEN)) {
//							proceedingJoinPoint.getArgs()[0].
						}
					}
				}
			}
		}
		
		Object[] args = proceedingJoinPoint.getArgs();
		Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }
}
