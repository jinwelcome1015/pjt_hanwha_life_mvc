package test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Test {

	private ClassLoader classLoader;

	/*
	public final String AES_KEY;
	public final String AES_IV;

	public Test() throws IOException {
		URL resource = this.getClass().getClassLoader().getResource("properties/qa.properties");
	
		InputStream openStream = resource.openStream();
	
		Properties properties = new Properties();
		properties.load(openStream);
		AES_KEY = properties.getProperty("interface.encrypt.aes-key");
		AES_IV = properties.getProperty("interface.encrypt.aes-iv");
	}
	*/
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, IOException, ClassNotFoundException, URISyntaxException {

		Test test = new Test();
//		test.doTest();
//		test.doFilterClassName();
		test.trimBase64prefix();

	}

	public void doTest() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		/*
		final String AES_KEY = "TzK5/8gFpMXmTKH5aYS6Uw9j2UwBGwGeju46fsJDwNE=";
		final String AES_IV = "vxg7xjhMOVsLDmPU+Wfp5g==";
		*/

		// /pjt_hanwha_life_mvc/src/main/resources/properties/qa.properties

		URL resource = this.getClass().getClassLoader().getResource("properties/qa.properties");

		InputStream openStream = resource.openStream();

		Properties properties = new Properties();
		properties.load(openStream);

		Set<Entry<Object, Object>> entrySet = properties.entrySet();

		entrySet.forEach(t -> System.out.println(t.getKey() + " = " + t.getValue()));
		/*
				final String AES_KEY = properties.getProperty("interface.encrypt.aes-key");
				final String AES_IV = properties.getProperty("interface.encrypt.aes-iv");
		*/
		/*
		AES_KEY = properties.getProperty("interface.encrypt.aes-key");
		AES_IV = properties.getProperty("interface.encrypt.aes-iv");
		*/
		/*
				String encrypt = AesUtil.encrypt("asdf", AES_KEY, AES_IV);
				System.out.println("encrypt : " + encrypt);
				String decrypt = AesUtil.decrypt(encrypt, AES_KEY, AES_IV);
				System.out.println("decrypt : " + decrypt);
		*/
	}
	
	public void doFilterClassName() throws ClassNotFoundException, URISyntaxException {
		String string = "com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto<com.gooroomee.gooroomeeadapter.dto.client.Mvc003ResDto>";
		String patternFrom = ".*\\<";
		String patternTo = "\\>.*";
		System.out.println("1 : " + string);
		string = string.replaceAll(patternFrom, "");
		System.out.println("2 : " + string);
		string = string.replaceAll(patternTo, "");
		System.out.println("3 : " + string);
		
		Class<?> forName = Class.forName(string);
		String simpleName = forName.getSimpleName();
		System.out.println(simpleName);
		String replaceAll = simpleName.replaceAll("\\D", "");
		System.out.println("replaceAll : " + replaceAll);
		
		
		ClassLoader classLoader = forName.getClassLoader();
		URL resource = classLoader.getResource(string);
		URI uri = resource.toURI();
		File file = new File(uri);
		System.out.println(file.length());
		
		/*
		String packageName = forName.getPackage().getName();
		System.out.println(packageName);
		File file = new File(packageName);
		String absolutePath = file.getAbsolutePath();
		System.out.println(absolutePath);
		System.out.println(file.listFiles());
		*/
		
	}
	
	
	public void trimBase64prefix() {
		String string = "data:image/png;base64,12345";
		String pattern = "^.+\\,";
		
		String replace = string.replaceAll(pattern, "");
		System.out.println(replace);
		
	}
}
