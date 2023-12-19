package com.gooroomee.backbone.external.dto.client;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Mvc999ReqDto {

//	/** 사용자ID */
//	private String userId;
	
//	/** 시스템코드 */
//	private String sysCode;

	/** 업무구분코드 */
	private String bswrvsnCode;
	
	/** 서류번호 (이미지관리번호) */
	private String imgeDocuNo;

	/** 서식코드 (서식분류체계 - 소분류코드) */
	private String dcfmCode;
	
	/** 문서유형코드 (서식분류체계 - 외부등록이미지코드) */
	private String dcmtTypeCode;
	
//	/** 서식바코드 */
//	private String dcfmBrcd;
	
//	/** 청약심사유형값 */
//	private String appnJdgnTypeVal;
	
//	/** 방카청약서여부 */
//	private String bncaAcfmYn;
	
//	/** 청약서수정여부 */
//	private String acfmAltrYn;
	
//	/** 계약자알릴의무사항여부 */
//	private String cntcBefrObdsMatrYn;
	
//	/** 이미지PREFIX값 */
//	private String imgePrefixVal;
	
//	/** 파일명 */
//	private String fileNm;
	
//	/** 원본파일명 */
//	private String ogtxFileNm; 
	
	/** 파일데이터 */
	private MultipartFile file;

}
