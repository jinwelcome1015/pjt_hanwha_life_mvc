package com.gooroomee.backbone.external.dto.ifconsumer.server;

import java.util.List;

import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs006_O.EmpeInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * <pre>
 * INTERFACE - 권한별사용자조회
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class IfMcCs004_O {

    /** 사용자리스트 */
	private List<User> userLstList;
	
	/** nextKey */
    private String nextKey;
    
    /** pageNumber */
    private int pageNumber;
	

    /**
     * 사용자 DTO 클래스
     * @author 신용진
     */
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
