package test;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.gooroomee.gooroomeeadapter.util.AesUtil;

public class Test {
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		
		final String AES_KEY = "TzK5/8gFpMXmTKH5aYS6Uw9j2UwBGwGeju46fsJDwNE=";
		final String AES_IV = "vxg7xjhMOVsLDmPU+Wfp5g==";

		String encrypt = AesUtil.encrypt("asdf", AES_KEY, AES_IV);
		System.out.println("encrypt : " + encrypt);
		String decrypt = AesUtil.decrypt(encrypt, AES_KEY, AES_IV);
		System.out.println("decrypt : " + decrypt);
	}
}
