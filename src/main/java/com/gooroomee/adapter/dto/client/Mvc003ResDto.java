package com.gooroomee.adapter.dto.client;

import com.gooroomee.adapter.dto.intrf.IfMcCs012_I.DataBody;
import com.gooroomee.adapter.dto.intrf.IfMcCs012_I.DataHeader;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Mvc003ResDto {
	/**
	 * 처리 성공 여부
	 */
	private String prcsSucsYn;

}
