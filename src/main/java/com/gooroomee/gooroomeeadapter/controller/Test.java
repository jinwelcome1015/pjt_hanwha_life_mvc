package com.gooroomee.gooroomeeadapter.controller;

import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class Test {
	public String name1() {
		return null;
	}
	
	public String name2() {
		return null;
	}
	
	public String getApis() {
		System.out.println("1111");
//		Class<?> thisClass = new Object() {}.getClass().getEnclosingClass();
//		Method[] declaredMethods = thisClass.getMethods();
//		Method[] declaredMethods = Test.class.getMethods();
		
		Method[] declaredMethods = this.getClass().getDeclaredMethods();
		System.out.println(declaredMethods.length);
		
		
		for (Method method : declaredMethods) {
			System.out.println("*****************1");
//			System.out.println(thisClass.getName());
			System.out.println(method.getName());
//			System.out.println(method.getAnnotation(PostMapping.class).path());
		}
		
		return "";
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		test.getApis();
	}
}
