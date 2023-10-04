package com.gooroomee.adapter.util;

import com.gooroomee.adapter.constant.TeleConstant;
import com.gooroomee.adapter.dto.HlicpMessageHeader;

public class ChannelAdapter {
	
	/**
	 * 송신 시스템 코드
	 */
	private static final String TRNM_SYS_CODE = "MVC";
	
	/**
	 * 채널 타입 코드
	 */
	private static final String CHNL_TYPE_CODE = "SVR";
	
	/**
	 * 소속 기관 코드
	 */
	private static final String BELN_ORGN_CODE = "00630";
	
	/**
	 * 사원 번호
	 */
	private String enmb;
	
	/**
	 * 활성 프로필(PROD, QA, DEV, LOCAL)
	 */
	private String activeProfile;
	
	/**
	 * 전송대상(MCI/ESB/FEB) URL 정보
	 */
	private String targetUrl;
	
	public ChannelAdapter(String enmb, String activeProfile, String targetUrl) {
		super();
		this.enmb = enmb;
		this.activeProfile = activeProfile;
		this.targetUrl = targetUrl;
	}
	
	public String getEnmb() {
		return enmb;
	}

	public void setEnmb(String enmb) {
		this.enmb = enmb;
	}

	public String getActiveProfile() {
		return activeProfile;
	}

	public void setActiveProfile(String activeProfile) {
		this.activeProfile = activeProfile;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	/**
	 * 
	 * @param itfcId
	 * @param rcveSrvcId
	 * @param rcveSysCode
	 * @param prsnInfoIncsYn
	 * @param emnb
	 * @return
	 */
	public HlicpMessageHeader createHeader(String itfcId, String rcveSrvcId, String rcveSysCode, String prsnInfoIncsYn, String emnb) {
		HlicpMessageHeader header = new HlicpMessageHeader();
		header.setItfcId(itfcId);
		header.setRcveSrvcId(rcveSrvcId);
		header.setChnlTypeCode(CHNL_TYPE_CODE);
		header.setTrnmSysCode(TRNM_SYS_CODE);
		header.setRcveSysCode(rcveSysCode);
		header.setEmnb(emnb);
		header.setBelnOrgnCode(BELN_ORGN_CODE);
		header.setPrsnInfoIncsYn(prsnInfoIncsYn);
		
		header.setIpAddr(TeleUtils.getIpAddress());
		header.setTlgrCretDttm(TeleUtils.getTlgrCretDttm());
		header.setRndmNo(rndmNo);
		header.setServerType(this.getServerType());
		header.setRspnDvsnCode(TeleConstant.RSPN_DVSN_SEND);
		
		return header;
	}

	private String getServerType() {
		String profile = this.getActiveProfile();
		String uvEnv = TeleConstant.SERVER_TYPE_PROD;
		
		if("local".equalsIgnoreCase(profile)) {
			uvEnv = TeleConstant.SERVER_TYPE_LOCAL;
		}else if("dev".equalsIgnoreCase(profile)) {
			uvEnv = TeleConstant.SERVER_TYPE_DEV;
		}else if("qa".equalsIgnoreCase(profile)) {
			uvEnv = TeleConstant.SERVER_TYPE_QA;
		}
		
		String serverType = "";
		
		if(TeleConstant.SERVER_TYPE_PROD.equalsIgnoreCase(uvEnv)) {
			serverType = "P";
		}else if(TeleConstant.SERVER_TYPE_QA.equalsIgnoreCase(uvEnv)) {
			serverType = "Q";
		}else if(TeleConstant.SERVER_TYPE_DEV.equalsIgnoreCase(uvEnv)) {
			serverType = "D";
		}
		
		return serverType;
	}
	
}
