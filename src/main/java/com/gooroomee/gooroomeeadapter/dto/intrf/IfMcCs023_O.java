package com.gooroomee.gooroomeeadapter.dto.intrf;

import java.util.List;

import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs006_O.EmpeInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IfMcCs023_O {
	
    /** 방문이슈고객월간휴일조회 */
	private List<DateInfo> dateInfoList;

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