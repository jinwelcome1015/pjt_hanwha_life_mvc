package com.gooroomee.adapter.util;

import com.gooroomee.adapter.constant.TeleConstant;
import com.gooroomee.adapter.dto.common.HlicpMessageHeader;

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
	private String targetBaseUrl;
	
	public ChannelAdapter(String enmb, String activeProfile, String targetBaseUrl) {
		super();
		this.enmb = enmb;
		this.activeProfile = activeProfile;
		this.targetBaseUrl = targetBaseUrl;
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
	
	public String getTargetBaseUrl() {
		return targetBaseUrl;
	}

	public void setTargetBaseUrl(String targetBaseUrl) {
		this.targetBaseUrl = targetBaseUrl;
	}

	public HlicpMessageHeader createHeader(String itfcId, String rcveSrvcId, String rcveSysCode) {
		return createHeader(itfcId, rcveSrvcId, rcveSysCode, "N");
	}
	
	
	public HlicpMessageHeader createHeader(String itfcId, String rcveSrvcId, String rcveSysCode, String prsnInfoIncsYn) {
		HlicpMessageHeader header = new HlicpMessageHeader();
		header.setItfcId(itfcId);
		header.setRcveSrvcId(rcveSrvcId);
		header.setChnlTypeCode(CHNL_TYPE_CODE);
		header.setTrnmSysCode(TRNM_SYS_CODE);
		header.setRcveSysCode(rcveSysCode);
		header.setEmnb(this.getEnmb());
		header.setBelnOrgnCode(BELN_ORGN_CODE);
		header.setPrsnInfoIncsYn(prsnInfoIncsYn);
		header.setIpAddr(TeleUtils.getLocalIpAddress());
		header.setTlgrCretDttm(TeleUtils.getTlgrCretDttm());
		header.setRndmNo(TeleUtils.getRandomNumber());
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
	
	public String getTargetFullUrl(TeleConstant.TargetType targetType) {
		
		String targetFullUrl = "";
		
		String targetBaseUrl = this.getTargetBaseUrl();
		
		if(targetType == TeleConstant.TargetType.MCI) {
			targetFullUrl = targetBaseUrl + "/mci" + "/" + TRNM_SYS_CODE.toLowerCase();
		}else if(targetType == TeleConstant.TargetType.ESB) {
			targetFullUrl = targetBaseUrl + "/esb";
		}else if(targetType == TeleConstant.TargetType.FEB) {
			targetFullUrl = targetBaseUrl + "/feb";
		}
		
		return targetFullUrl;
	}
	
	
}
