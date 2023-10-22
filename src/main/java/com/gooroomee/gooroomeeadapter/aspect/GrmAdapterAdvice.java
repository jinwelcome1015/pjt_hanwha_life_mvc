package com.gooroomee.gooroomeeadapter.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class GrmAdapterAdvice {
	
	@Around("execution(* com.gooroomee.gooroomeeadapter.controller.*.*(..))")
    public Object responseWithMockData(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
        return null;
    }
}
