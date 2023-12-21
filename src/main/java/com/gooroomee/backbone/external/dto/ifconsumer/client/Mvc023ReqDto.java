package com.gooroomee.backbone.external.dto.ifconsumer.client;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc023ReqDto {
	
	/** 사원번호 */
	private String emnb;

	/** 기준년월 (yyyy 또는 yyyyMM)*/
    private String stndYymm;

}
