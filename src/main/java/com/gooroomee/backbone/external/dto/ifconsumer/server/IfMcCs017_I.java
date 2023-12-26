package com.gooroomee.backbone.external.dto.ifconsumer.server;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * INTERFACE - 대체키별연락처조회
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class IfMcCs017_I {

	/** 대체키SUID */
	private BigInteger sbsnKeySuid;
}
