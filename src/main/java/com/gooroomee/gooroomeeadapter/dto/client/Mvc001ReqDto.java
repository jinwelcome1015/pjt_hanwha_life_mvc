package com.gooroomee.gooroomeeadapter.dto.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc001ReqDto {
	
	/** mock 응답 사용여부 */
	public String useMockResponseYn;

	/** 사원번호 */
	public String emnb;
	
	/** 사용자 ID */
	private String USER_ID;
	
	/** 이미지 포맷 */
	private String format;

	/** 이미지 데이터 */
	private String data;

	/** 이미지 이름 */
	private String name;
	
}
