package com.gooroomee.backbone.external.dto.ifconsumer.client;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc004ResDto {

	// XXX 확인
    /** 사용자리스트 */
	private List<User> userLstList;
	
	/** nextKey */
    private String nextKey;
	
    /** pageNumber */
    private int pageNumber;

	@Getter
	@Setter
	@ToString
	public static class User {
		
	    /** 보험코어권한ID */
	    private String isrnCoreAtrtId;

	    /** 보험코어권한명 */
	    private String isrnCoreAtrtNm;
	    
	    /** 보험코어권한설명내용 */
	    private String isrnCoreAtrtIstcCntn;
	    
	    /** 사원번호 */
	    private String emnb;
	    
	    /** 사원명 */
	    private String empeNm;
	    
	    /** 기관코드 */
	    private String orgnCode;
	    
	    /** 기관명 */
	    private String orgnNm;

	}

}
