package com.gooroomee.backbone.external.component;

import java.io.IOException;

/**
 * 구루미 화상상담 시스템 입장 URI를 제공하는 getConnectableUri 메서드를 지닌 인터페이스
 * @author 신용진
 */
public interface GrmCounsellingOtpUriSupplier {
	
	/**
	 * 구루미 화상상담 시스템 입장 URI를 반환하는 메서드
	 * @return 구루미 화상상담 시스템 입장 URI
	 * @throws IOException
	 */
	String getConnectableUri() throws IOException;
}
