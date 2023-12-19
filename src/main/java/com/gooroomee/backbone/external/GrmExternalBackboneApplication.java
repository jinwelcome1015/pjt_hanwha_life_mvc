package com.gooroomee.backbone.external;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 이 어플리케이션의 entry point 가 되는, main 메서드를 갖고 있는 클래스
 * @author 신용진
 */
@SpringBootApplication
public class GrmExternalBackboneApplication {
	
	/**
	 * 이 어플리케이션의 entry point 메서드
	 * @param args 이 어플리케이션이 기동될때  전달 받은 인자
	 */
	public static void main(String[] args) {
		SpringApplication.run(GrmExternalBackboneApplication.class, args);
	}

}
