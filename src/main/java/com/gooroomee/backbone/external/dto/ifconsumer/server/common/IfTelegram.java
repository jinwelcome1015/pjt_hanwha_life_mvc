package com.gooroomee.backbone.external.dto.ifconsumer.server.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 인터페이스 전문 공통 DTO
 * @author 신용진
 *
 * @param <P> 인터페이스 전문 payload 타입
 */
@Getter 
@Setter
@ToString
public class IfTelegram<P> {
	private IfTelegramHeader header;
	private P payload;
}
