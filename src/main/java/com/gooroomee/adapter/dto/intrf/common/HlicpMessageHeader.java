package com.gooroomee.adapter.dto.intrf.common;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter 
@Setter
@ToString
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
	
}
