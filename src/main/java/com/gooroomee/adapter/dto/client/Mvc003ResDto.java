package com.gooroomee.adapter.dto.client;

import com.gooroomee.adapter.dto.client.common.ResSuperDto;
import com.gooroomee.adapter.dto.intrf.IfMcCs012_I.DataBody;
import com.gooroomee.adapter.dto.intrf.IfMcCs012_I.DataHeader;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Mvc003ResDto extends ResSuperDto{
	/** 처리성공여부 */
	private String prcsSucsYn
;

}
