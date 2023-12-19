package com.gooroomee.backbone.external.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * AES 암호화 utility 클래스
 * @author 신용진
 */
public class AesUtil {

	/**
	 * 평문을 암호화 한다.
	 * @param plain 암호화할 평문
	 * @param key 암호화 key
	 * @param iv 암호화 iv
	 * @return 암호화 결과
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String encrypt(String plain, String key, String iv) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(key.getBytes()), "AES"),
				new IvParameterSpec(Base64.decodeBase64(iv.getBytes())));

		byte[] bytes = cipher.doFinal(plain.getBytes(StandardCharsets.UTF_8));
		return new String(Base64.encodeBase64(bytes));
	}

	
	/**
	 * 복호화해서 평문으로 만든다.
	 * @param encrypted 복호화할 암호화문
	 * @param key 복호화 key
	 * @param iv 복호화 iv
	 * @return 복호화 결과
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String decrypt(String encrypted, String key, String iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(key.getBytes()), "AES"),
				new IvParameterSpec(Base64.decodeBase64(iv.getBytes())));
		byte[] bytes = cipher.doFinal(Base64.decodeBase64(encrypted.getBytes()));
		return new String(bytes, StandardCharsets.UTF_8);
	}

}
