package com.gooroomee.gooroomeeadapter.util;

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

public class AesUtil {

	public static String encrypt(String plain, String key, String iv) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(key.getBytes()), "AES"),
				new IvParameterSpec(Base64.decodeBase64(iv.getBytes())));

		byte[] bytes = cipher.doFinal(plain.getBytes(StandardCharsets.UTF_8));
		return new String(Base64.encodeBase64(bytes));
	}

	public static String decrypt(String encrypted, String key, String iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(key.getBytes()), "AES"),
				new IvParameterSpec(Base64.decodeBase64(iv.getBytes())));
		byte[] bytes = cipher.doFinal(Base64.decodeBase64(encrypted.getBytes()));
		return new String(bytes, StandardCharsets.UTF_8);
	}

}
