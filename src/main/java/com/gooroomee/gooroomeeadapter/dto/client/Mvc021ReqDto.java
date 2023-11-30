package com.gooroomee.gooroomeeadapter.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc021ReqDto {

	/** 보험코어권한ID */
    private String isrnCoreAtrtId;

    /** 기관코드 */
    private String orgnCode;

}
