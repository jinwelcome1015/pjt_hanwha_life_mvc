package com.gooroomee.backbone.external.dto.ifconsumer.server;

import java.util.List;

import com.gooroomee.backbone.external.dto.ifconsumer.server.IfMcCs006_O.EmpeInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/**
 * <pre>
 * INTERFACE - 해당년월일자정보목록조회
 * 
 * 응답 DTO
 * </pre>
 * @author 신용진
 */
@Getter
@Setter
@ToString
public class IfMcCs023_O {
	
    /** 일자정보 */
	private List<DateInfo> dateInfoList;

	/**
	 * 일자정보 DTO 클래스
	 * @author 신용진
	 */
	@Getter
	@Setter
	@ToString
	public static class DateInfo {
	    
	    /** 기준년도 */
	    private String stndYear;
	    
	    /** 기준요일코드 */
	    private String stndDywkCode;
	    
	    /** 기준월 */
	    private String stndMnth;
	    
	    /** 기준일 */
	    private String stndDay;
	    
	    /** 기준일자 */
	    private String rfdt;
	    
	    /** 사고보험휴일여부 */
	    private String acdtIsrnHldyYn;
	    
	    /** 영업휴무일여부 */
	    private String bsnsOfdyYn;
	    
	    /** 주식휴무일여부 */
	    private String stckOfdyYn;
	    
	    /** 특정일내용 */
	    private String spcfDayCntn;
	    
	    /** 휴일여부 */
	    private String hldyYn;
	    
	    /** 전자청약휴무일여부 */
	    private String elctAppnOfdyYn;

	}
}
