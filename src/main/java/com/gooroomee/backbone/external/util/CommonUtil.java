package com.gooroomee.backbone.external.util;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 일단 utility 클래스
 * @author 신용진
 */
public class CommonUtil {
	/*
	public static void main(String[] args) {
		String string = "List<Map<String, Map<String, Object>>>";
		
		CommonUtil commonUtil = new CommonUtil();
		String extractTypeParameterClassName = commonUtil.extractTypeParameterClassName(string);
		System.out.println(extractTypeParameterClassName);
	}
	*/
	public static String extractTypeParameterClassName(String originClassName) {
		String typeParameterClassName = originClassName;
		typeParameterClassName = typeParameterClassName.replaceFirst("^(.*\\<)", "");
		typeParameterClassName = typeParameterClassName.replaceFirst("(\\>.*)$", "");
		return typeParameterClassName;
	}
	

	/**
	 * 현재 시간을 yyyyMMddHHmmssSSS 형식의 문자열로 반환한다.
	 * @return 현재 시간(yyyyMMddHHmmssSSS 형식)
	 */
	public static String getNowYyyyMMddHHmmssSSS() {
		String pattern = "yyyyMMddHHmmssSSS";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = new Date();
		return simpleDateFormat.format(date);
	}

	
	/**
	 * 4자리 임의번호 문자열을 반환한다.
	 * @return 4자리 임의번호 문자열
	 */
	public static String getRandomNumber() {
		return getRandomNumber(4);
	}

	/**
	 * 입력받은 자리수만큼의 임의번호 문자열을 만들어 반환한다.
	 * @param digits 생성할 임의번호 문자열의 자리수
	 * @return 생성된 임의번호 문자열
	 */
	public static String getRandomNumber(int digits) {
		if (digits < 1) {
			throw new IllegalArgumentException("자리수는 양수값이어야 합니다.");
		}

		int exclusiveUpperBound = (int) Math.pow(10, digits);
		SecureRandom secureRandom = new SecureRandom();
		int nextInt = secureRandom.nextInt(exclusiveUpperBound);

		String formattedRandomNumber = String.format(("%0" + digits + "d"), nextInt);

		return formattedRandomNumber;
	}
}
