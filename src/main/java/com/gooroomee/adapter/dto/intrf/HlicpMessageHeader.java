package com.gooroomee.adapter.dto.intrf;

import java.util.List;

public class HlicpMessageHeader {
	
	// XXX [BOC] 시스템 공통부
	
	/**
	 * 송신 시스템 코드
	 */
	private String trnmSysCode;
	
	/**
	 * IP 주소
	 */
	private String ipAddr;
	
	/**
	 * 전문 생성 일시
	 */
	private String tlgrCretDttm;
	
	/**
	 * 임의 번호
	 */
	private String rndmNo;
	
	/**
	 * 이력 번호
	 */
	private int hsno;
	
	/**
	 * 인증 토큰
	 */
	private String ctfnTokn;
	
	/**
	 * 원거래 거래 번호
	 */
	private String ogtsTrnnNo;
	
	/**
	 * 개인정보 포함 여부
	 */
	private String prsnInfoIncsYn;
	
	/**
	 * 인터페이스 ID
	 */
	private String itfcId;
	
	/**
	 * 수신 서비스 ID
	 */
	private String rcveSrvcId;
	
	/**
	 * 수신 시스템 코드
	 */
	private String rcveSysCode;
	
	/**
	 * MCI 노드 번호
	 */
	private String mciNodeNo;
	
	/**
	 * MCI 세션 ID
	 */
	private String mciSesnId;
	
	/**
	 * 서버 유형
	 */
	private String serverType;
	
	/**
	 * 요청 응답 구분 코드
	 */
	private String rspnDvsnCode;
	
	/**
	 * 당타발 구분 코드
	 */
	private String extlDvsnCode;
	
	// XXX [EOC] 시스템 공통부
	
	// XXX [BOC] 요청 정보
	
	/**
	 * 사원 번호
	 */
	private String emnb;
	
	/**
	 * 소속 기관 코드
	 */
	private String belnOrgnCode;
	
	/**
	 * 고객 ID
	 */
	private String custId;
	
	/**
	 * 채널 유형 코드
	 */
	private String chnlTypeCode;
	
	/**
	 * 거래 발생 화면 ID
	 */
	private String scrnId;
	
	/**
	 * 거래 발생 이전 화면 ID
	 */
	private String befoScrnId;
	
	/**
	 * 사용자 단말기 식별값
	 */
	private String userTmunIdnfVal;
	
	/**
	 * 요청자 IP
	 */
	private String rqsrIp;
	
	/**
	 * 최초 전송 시간
	 */
	private String rqstDttm;
	
	/**
	 * 통화 단위
	 */
	private String baseCrny;
	
	/**
	 * 국가 정보
	 */
	private String baseCnty;
	
	/**
	 * 사용 언어
	 */
	private String baseLang;
	
	// XXX [EOC] 요청 정보
	
	// XXX [BOC] 응답 정보
	
	/**
	 * 전문 응답 일시
	 */
	private String tlgrRspnDttm;
	
	/**
	 * 처리 결과 구분 코드
	 */
	private String prcsRsltDvsnCode;
	
	/**
	 * 페이지 카운트
	 */
	private int totalCount;
	
	/**
	 * 마지막 페이지 여부
	 */
	private String lastPageYn;
	
	// XXX [EOC] 응답 정보
	
	// XXX [BOC] 메시지
	
	/**
	 * 메시지 목록 건수
	 */
	private int msgeListCont;
	
	/**
	 * 메시지 목록
	 */
	private List<HlicpResponseMessage> msgeList;
	
	/**
	 * 에러 내용
	 */
	private String msgeStackTrace;

	// XXX [EOC] 메시지

	
	public String getTrnmSysCode() {
		return trnmSysCode;
	}

	public void setTrnmSysCode(String trnmSysCode) {
		this.trnmSysCode = trnmSysCode;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getTlgrCretDttm() {
		return tlgrCretDttm;
	}

	public void setTlgrCretDttm(String tlgrCretDttm) {
		this.tlgrCretDttm = tlgrCretDttm;
	}

	public String getRndmNo() {
		return rndmNo;
	}

	public void setRndmNo(String rndmNo) {
		this.rndmNo = rndmNo;
	}

	public int getHsno() {
		return hsno;
	}

	public void setHsno(int hsno) {
		this.hsno = hsno;
	}

	public String getCtfnTokn() {
		return ctfnTokn;
	}

	public void setCtfnTokn(String ctfnTokn) {
		this.ctfnTokn = ctfnTokn;
	}

	public String getOgtsTrnnNo() {
		return ogtsTrnnNo;
	}

	public void setOgtsTrnnNo(String ogtsTrnnNo) {
		this.ogtsTrnnNo = ogtsTrnnNo;
	}

	public String getPrsnInfoIncsYn() {
		return prsnInfoIncsYn;
	}

	public void setPrsnInfoIncsYn(String prsnInfoIncsYn) {
		this.prsnInfoIncsYn = prsnInfoIncsYn;
	}

	public String getItfcId() {
		return itfcId;
	}

	public void setItfcId(String itfcId) {
		this.itfcId = itfcId;
	}

	public String getRcveSrvcId() {
		return rcveSrvcId;
	}

	public void setRcveSrvcId(String rcveSrvcId) {
		this.rcveSrvcId = rcveSrvcId;
	}

	public String getRcveSysCode() {
		return rcveSysCode;
	}

	public void setRcveSysCode(String rcveSysCode) {
		this.rcveSysCode = rcveSysCode;
	}

	public String getMciNodeNo() {
		return mciNodeNo;
	}

	public void setMciNodeNo(String mciNodeNo) {
		this.mciNodeNo = mciNodeNo;
	}

	public String getMciSesnId() {
		return mciSesnId;
	}

	public void setMciSesnId(String mciSesnId) {
		this.mciSesnId = mciSesnId;
	}

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	public String getRspnDvsnCode() {
		return rspnDvsnCode;
	}

	public void setRspnDvsnCode(String rspnDvsnCode) {
		this.rspnDvsnCode = rspnDvsnCode;
	}

	public String getExtlDvsnCode() {
		return extlDvsnCode;
	}

	public void setExtlDvsnCode(String extlDvsnCode) {
		this.extlDvsnCode = extlDvsnCode;
	}

	public String getEmnb() {
		return emnb;
	}

	public void setEmnb(String emnb) {
		this.emnb = emnb;
	}

	public String getBelnOrgnCode() {
		return belnOrgnCode;
	}

	public void setBelnOrgnCode(String belnOrgnCode) {
		this.belnOrgnCode = belnOrgnCode;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getChnlTypeCode() {
		return chnlTypeCode;
	}

	public void setChnlTypeCode(String chnlTypeCode) {
		this.chnlTypeCode = chnlTypeCode;
	}

	public String getScrnId() {
		return scrnId;
	}

	public void setScrnId(String scrnId) {
		this.scrnId = scrnId;
	}

	public String getBefoScrnId() {
		return befoScrnId;
	}

	public void setBefoScrnId(String befoScrnId) {
		this.befoScrnId = befoScrnId;
	}

	public String getUserTmunIdnfVal() {
		return userTmunIdnfVal;
	}

	public void setUserTmunIdnfVal(String userTmunIdnfVal) {
		this.userTmunIdnfVal = userTmunIdnfVal;
	}

	public String getRqsrIp() {
		return rqsrIp;
	}

	public void setRqsrIp(String rqsrIp) {
		this.rqsrIp = rqsrIp;
	}

	public String getRqstDttm() {
		return rqstDttm;
	}

	public void setRqstDttm(String rqstDttm) {
		this.rqstDttm = rqstDttm;
	}

	public String getBaseCrny() {
		return baseCrny;
	}

	public void setBaseCrny(String baseCrny) {
		this.baseCrny = baseCrny;
	}

	public String getBaseCnty() {
		return baseCnty;
	}

	public void setBaseCnty(String baseCnty) {
		this.baseCnty = baseCnty;
	}

	public String getBaseLang() {
		return baseLang;
	}

	public void setBaseLang(String baseLang) {
		this.baseLang = baseLang;
	}

	public String getTlgrRspnDttm() {
		return tlgrRspnDttm;
	}

	public void setTlgrRspnDttm(String tlgrRspnDttm) {
		this.tlgrRspnDttm = tlgrRspnDttm;
	}

	public String getPrcsRsltDvsnCode() {
		return prcsRsltDvsnCode;
	}

	public void setPrcsRsltDvsnCode(String prcsRsltDvsnCode) {
		this.prcsRsltDvsnCode = prcsRsltDvsnCode;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getLastPageYn() {
		return lastPageYn;
	}

	public void setLastPageYn(String lastPageYn) {
		this.lastPageYn = lastPageYn;
	}

	public int getMsgeListCont() {
		return msgeListCont;
	}

	public void setMsgeListCont(int msgeListCont) {
		this.msgeListCont = msgeListCont;
	}

	public List<HlicpResponseMessage> getMsgeList() {
		return msgeList;
	}

	public void setMsgeList(List<HlicpResponseMessage> msgeList) {
		this.msgeList = msgeList;
	}

	public String getMsgeStackTrace() {
		return msgeStackTrace;
	}

	public void setMsgeStackTrace(String msgeStackTrace) {
		this.msgeStackTrace = msgeStackTrace;
	}

	@Override
	public String toString() {
		return "HlicpMessageHeader [trnmSysCode=" + trnmSysCode + ", ipAddr=" + ipAddr + ", tlgrCretDttm="
				+ tlgrCretDttm + ", rndmNo=" + rndmNo + ", hsno=" + hsno + ", ctfnTokn=" + ctfnTokn + ", ogtsTrnnNo="
				+ ogtsTrnnNo + ", prsnInfoIncsYn=" + prsnInfoIncsYn + ", itfcId=" + itfcId + ", rcveSrvcId="
				+ rcveSrvcId + ", rcveSysCode=" + rcveSysCode + ", mciNodeNo=" + mciNodeNo + ", mciSesnId=" + mciSesnId
				+ ", serverType=" + serverType + ", rspnDvsnCode=" + rspnDvsnCode + ", extlDvsnCode=" + extlDvsnCode
				+ ", emnb=" + emnb + ", belnOrgnCode=" + belnOrgnCode + ", custId=" + custId + ", chnlTypeCode="
				+ chnlTypeCode + ", scrnId=" + scrnId + ", befoScrnId=" + befoScrnId + ", userTmunIdnfVal="
				+ userTmunIdnfVal + ", rqsrIp=" + rqsrIp + ", rqstDttm=" + rqstDttm + ", baseCrny=" + baseCrny
				+ ", baseCnty=" + baseCnty + ", baseLang=" + baseLang + ", tlgrRspnDttm=" + tlgrRspnDttm
				+ ", prcsRsltDvsnCode=" + prcsRsltDvsnCode + ", totalCount=" + totalCount + ", lastPageYn=" + lastPageYn
				+ ", msgeListCont=" + msgeListCont + ", msgeList=" + msgeList + ", msgeStackTrace=" + msgeStackTrace
				+ "]";
	}
	
}
