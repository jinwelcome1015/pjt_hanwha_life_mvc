package com.gooroomee.gooroomeeadapter.dto.client;

import com.gooroomee.gooroomeeadapter.dto.client.common.ReqSuperDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc017ReqDto extends ReqSuperDto {

	// XXX 확인
	/** 대체키SUID */
	private long sbsnKeySuid;
}
