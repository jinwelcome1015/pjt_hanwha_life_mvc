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
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gooroomee.backbone.external.util.AesUtil;

import korealife.uv.com.cm.SHA256CmCrypt;

public class Test {
	
//	private static final Logger logger = Test.class.getname

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
//		test.trimBase64prefix();
		
//		test.encrypt();
//		test.regex();
//		test.sex();
//		test.log();
//		test.urlPattern();
		test.jsonNode();
	}

	private void jsonNode() throws JsonMappingException, JsonProcessingException {
		String aaa = "{\r\n" + 
				"			\"a_1\" : {\r\n" + 
				"				\"b_1\" : {\r\n" + 
				"					\"c_1\" : \"가\",\r\n" + 
				"					\"c_2\" : \"나\",\r\n" + 
				"					\"c_3\" : \"다\"\r\n" + 
				"				}\r\n" + 
				"			}\r\n" + 
				"		}";
				
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(aaa);
		JsonNode deepCopyJsonNode = jsonNode.deepCopy();
		ObjectNode objectNode = ObjectNode.class.cast(deepCopyJsonNode.get("a_1").get("b_1"));
//		ObjectNode objectNode = ObjectNode.class.cast(jsonNode.get("a_1").get("b_1"));
		objectNode.put("c_2", "");
		
		System.out.println(String.format("[jsonNode] : %s", jsonNode));
		System.out.println(String.format("[deepCopyJsonNode] : %s", deepCopyJsonNode));
		System.out.println(String.format("[deepCopyJsonNode] : %s", deepCopyJsonNode.toString()));
		System.out.println(String.format("[objectNode] : %s", objectNode));
	}

	private void sex() {
		String[] personalNumberTokens = {"791015", "3055429"};
		String sex = (Integer.parseInt(personalNumberTokens[1].substring(0, 1)) % 2) == 1 ? "M" : "F";
		System.out.println(sex);
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
		String string = "com.gooroomee.backbone.external.dto.client.common.ResponseDto<com.gooroomee.backbone.external.dto.client.Mvc003ResDto>";
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
	
	public void encrypt() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
//		String plainText = "grm";
//		String sha256_getEncString = SHA256CmCrypt.SHA256_getEncString(plainText);
//		System.out.println(sha256_getEncString);
		
		String plain = "gooroomee";
		String key = "TzK5/8gFpMXmTKH5aYS6Uw9j2UwBGwGeju46fsJDwNE=";
		String iv = "vxg7xjhMOVsLDmPU+Wfp5g==";
		String encrypt = AesUtil.encrypt(plain, key, iv);
		String decrypt = AesUtil.decrypt(encrypt, key, iv);
		
		System.out.println(plain);
		System.out.println(encrypt);
		System.out.println(decrypt);
	}
	
	
	public void regex() {
//		String url = "https://abc.com";
		String url = "http://abc.com";
		String regex = "^http://|^https://";
		
		System.out.println(url.replaceAll(regex, ""));
	}
	
	public void log() {
		
		System.out.println();
	}
	
	public void urlPattern() {
		String a = "/grm/adapter/intrf/idcdOcrRqst/mock/1";
		String m = ".*/mock/\\d";
//		Pattern pattern = Pattern.compile(m);
		boolean matches = Pattern.matches(m, a);
		
		System.out.println(matches);
		
		String replaceAll = a.replaceAll(".*mock/", "");
		
		System.out.println("replaceAll : " + replaceAll);
	}
	
}
