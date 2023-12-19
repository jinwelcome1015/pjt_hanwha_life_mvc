package com.gooroomee.backbone.external;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 이 어플리케이션을 WAR로 배포할 경우, 서블릿 컨테이너에 이 어플리케이션을 등록하기 위한 configure 메서드를 갖고 있는 클래스
 * @author 신용진
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GrmExternalBackboneApplication.class);
	}

}
