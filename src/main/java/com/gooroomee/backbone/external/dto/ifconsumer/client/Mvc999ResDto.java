package com.gooroomee.backbone.external.dto.ifconsumer.client;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * API - 이미지 시스템 등록
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class Mvc999ResDto {

	/** 트랜잭션ID */
	private String trscId;
	
	/** 수행성공여부(Y/N) */
	private String rsltYn;
	
	/** 결과메시지. 특별한 경우를 제외한 일반적으로 rsltYn="N"일 경우에 있음. */
	private String rsltMsge;
	
	/** 결과 에러 코드. 특별한 경우를 제외한 일반적으로 rsltYn="N"일 경우에 있음. */
	private String rsltErroCode;

}
