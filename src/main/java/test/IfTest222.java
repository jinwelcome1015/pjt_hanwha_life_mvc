package test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gooroomee.backbone.external.constant.IfConstant;
import com.gooroomee.backbone.external.constant.IfConstant.IfSpec;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs001_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs001_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs002_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs002_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs003_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs003_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs005_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs005_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs006_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs006_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs007_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs007_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs008_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs008_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs009_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs009_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs010_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs010_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs011_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs011_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs012_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs012_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs013_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs013_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs014_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs014_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs015_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs015_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs016_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs016_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs017_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs017_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs018_I;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs018_O;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs001_I.DataBody;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs001_I.DataHeader;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs001_I.DataBody.Image;
import com.gooroomee.backbone.external.dto.intrf.IfMcCs011_I.DataBody.Callback;
import com.gooroomee.backbone.external.dto.intrf.common.IfTelegram;
import com.gooroomee.backbone.external.dto.intrf.common.IfTelegramHeader;
import com.gooroomee.backbone.external.util.AesUtil;
import com.gooroomee.backbone.external.util.IfUtil;

import korealife.uv.com.cm.SHA256CmCrypt;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class IfTest222 {

    private static final Logger logger = LoggerFactory.getLogger(IfTest222.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
    													.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    													.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
    													.registerModule(new SimpleModule())
    													;
    
    
    public static final RestTemplate REST_TEMPLATE = new RestTemplate();
    

    public static final String EMNB = "1077593";
//    public static final String EMNB = "1077123";
    //    public static final String EMNB = "1073818";

    
    public static final String ACTIVE_PROFILE = "qa";
    public static final String IF_ENDPOINT_URL = "https://qainf.hanwhalife.com:8713";
    
	/*
	public static final String ACTIVE_PROFILE = "dev";
	public static final String IF_ENDPOINT_URL = "http://devinf.hanwhalife.com:7711";
	*/

    public static final String ORGN_CODE = "00630";
    
    public static final String OCR_URL = "https://kyxtjoiqax.apigw-pub.fin-ntruss.com/custom/v1/5129/c6aba620e83d8ca37e578ac6f8c5211267b5b93279ea2fe205a0708ef0f5ac4c/document/id-card ";

    public static final String X_OCR_SECRET = "ckZJZ29HcG1OZFF5WXJ3TXBYSXZlbUJzWmhGbmx6Ylc=";
    
    public static final String SCRN_ID = "";
    
    public static final String SRVC_ID = "SVC028";
    
    public static final String AES_KEY = "TzK5/8gFpMXmTKH5aYS6Uw9j2UwBGwGeju46fsJDwNE=";
    public static final String AES_IV = "vxg7xjhMOVsLDmPU+Wfp5g==";
    
    
    public static final String TOKEN_TYPE = "Bearer";
    public static final String INITECH_O_AUTH_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvYXV0aC5pbmlodWIuYml6Iiwic3ViIjoiYWNjZXNzVG9rZW4iLCJleHAiOjE2OTg2NDQ4MzAsImp0aSI6ImdOOUNFTlZsUGFaRmdvWjY0a0dKIiwiY2xpZW50X2lkIjoiMDFlMjM4ZWYtODAyNS00MjgzLTk4M2YtOWY1ZDIyMWU4ODVlIn0.qZyF8Owy9t9t2V5K0t_3hkH5uAr5Pwpfi1tirOZgvjQlV8zkF2a_XAWMCwqpNnqiYRd-Ufn9ys9sncVCiTvxQ-yXmdR76A6et7O6MGw5r3wc-KiyICvGP7i_rjr-NaJsMhdmcykwzXAiw48eO5aqEx94BSI3JaUtWcvnoYUz-1octLzvrD1ocurCXnlcNrL9Z_r7--ZOEMrUlN8IRPN-FB7L5Tq2sGWsGjX-bV1ogXYuu7R4bt6So1DWkiThu1dlgw0FWci4Bll3hUaBY2DrncGE49mWXPH6yOxvSBq1lEIEK3KopAXxMcZF1PbknQaEFvaN7MHb0Q5kXXmv72X2fg";
    public static final String REQ_TX_ID = "55df29e8-79b2-44d6-82b5-cb4c7cf9449f";
    
    
    
    @Getter
    @Setter
    @ToString
    public static class InputPayloadDto{
        private String custNm;
        private String custId;
        private String custTlno;
        private String rqstOrgnCode;
    }
    
    
    @Getter
    @Setter
    @ToString
    public static class OutputPayloadDto{
        private String dvsnVal;
        private String rsltMsgeCntn;
        private String rsltDatVal;
    }
    
    
    
    
    
    /*
    public IfTest() throws IOException {
    	
    	final String activeProfile = "qa"; 
    	
    	URL resource = this.getClass().getClassLoader().getResource("properties/" + activeProfile + ".properties");
    	
    	InputStream openStream = resource.openStream();
    	
    	Properties properties = new Properties();
    	properties.load(openStream);
    	
    	AES_KEY = properties.getProperty("interface.encrypt.aes-key");
    	AES_IV = properties.getProperty("interface.encrypt.aes-iv");
    }
    */
    

    public static void main(String[] args) throws URISyntaxException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
                IfTest222 ifTest = new IfTest222();
//                ifTest.doTest_00001();
//                ifTest.doTest_00002();
//                ifTest.doTest_encryptDecrypt();
        
//                ifTest.doTest_jacksonPascalCase();
                
                
//                ifTest.testIf00A();
        
                // XXX 성공
//                ifTest.testIf001();
                
        
                // XXX 성공
//                ifTest.testIf002();

        
                // XXX 성공
//                ifTest.testIf003();
        
        
                // XXX 성공
//                ifTest.testIf005();


                // XXX 성공
//                ifTest.testIf006();ㅓ

                // XXX 성공
//                ifTest.testIf007();

                // XXX 성공        
//                ifTest.testIf008();

                // XXX 성공
//                ifTest.testIf009();

                // XXX 성공
//                ifTest.testIf010();

                // XXX 성공
//                ifTest.testIf011();
                
                // XXX 성공
//                ifTest.testIf012();
        
                // XXX 실패
//                ifTest.testIf015_1();

                // XXX 성공
//                ifTest.testIf015_2();
        
                // XXX 성공
//                ifTest.testIf016();
        
                // XXX 성공
//                ifTest.testIf017();
                
                // XXX 성공
//                ifTest.testIf018();
                
                // XXX 성공
//                ifTest.testIf013();
                
                // XXX                 
                ifTest.testIf014();
    }
    
    
    
    
    
    
    /**
     * IVR 연계
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf00A() throws JsonProcessingException, URISyntaxException {
        
        
        
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();

        String payloadJson = "{\r\n" + 
                "    \"custNm\" : \"곽우섭\",\r\n" + 
                "    \"custId\" : \"9024777049\",\r\n" + 
                "    \"custTlno\" : \"01022340080\",\r\n" + 
                "    \"rqstOrgnCode\" : \"mcc02\"\r\n" + 
                "}";
        
        
        InputPayloadDto inputPayload = OBJECT_MAPPER.readValue(payloadJson, InputPayloadDto.class);
        
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
        
        String inputHeaderJson = "{\r\n" + 
                "    \"trnmSysCode\": \"IVR\",\r\n" + 
                "    \"rcveSysCode\": \"MVC\",\r\n" + 
                "    \"itfcId\": \"HLIIVR00122\",\r\n" + 
                "    \"rcveSrvcId\": \"mvcCounsellingOtp001r\",\r\n" + 
                "    \"trnnNo\": null,\r\n" + 
                "    \"ipAddr\": \"010252005065\",\r\n" + 
                "    \"tlgrCretDttm\": \"20231121092151038\",\r\n" + 
                "    \"rndmNo\": \"0020\",\r\n" + 
                "    \"hsno\": 0,\r\n" + 
                "    \"ctfnTokn\": null,\r\n" + 
                "    \"ogtsTrnnNo\": null,\r\n" + 
                "    \"prsnInfoIncsYn\": \"N\",\r\n" + 
                "    \"mciNodeNo\": null,\r\n" + 
                "    \"mciSesnId\": null,\r\n" + 
                "    \"serverType\": \"Q\",\r\n" + 
                "    \"rspnDvsnCode\": \"S\",\r\n" + 
                "    \"extlDvsnCode\": null,\r\n" + 
                "    \"emnb\": \"10777777\",\r\n" + 
                "    \"belnOrgnCode\": \"00630\",\r\n" + 
                "    \"custId\": null,\r\n" + 
                "    \"chnlTypeCode\": \"SVR\",\r\n" + 
                "    \"scrnId\": null,\r\n" + 
                "    \"befoScrnId\": null,\r\n" + 
                "    \"userTmunIdnfVal\": null,\r\n" + 
                "    \"rqsrIp\": null,\r\n" + 
                "    \"rqstDttm\": null,\r\n" + 
                "    \"baseCrny\": null,\r\n" + 
                "    \"baseCnty\": null,\r\n" + 
                "    \"baseLang\": null,\r\n" + 
                "    \"tlgrRspnDttm\": null,\r\n" + 
                "    \"prcsRsltDvsnCode\": null,\r\n" + 
                "    \"totalCount\": 0,\r\n" + 
                "    \"lastPageYn\": null,\r\n" + 
                "    \"msgeListCont\": 0,\r\n" + 
                "    \"msgeList\": null,\r\n" + 
                "    \"msgeStackTrace\": null\r\n" + 
                "}";  
        

//        IfTelegramHeader inputHeader = new IfTelegramHeader();
        IfTelegramHeader inputHeader = OBJECT_MAPPER.readValue(inputHeaderJson, IfTelegramHeader.class);
        
        
        IfTelegram<OutputPayloadDto> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                OutputPayloadDto.class);

        OutputPayloadDto outputPayload = outputTelegram.getPayload();

//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }
    
    
    
    

    
    
    /**
     * 신분증OCR요청 
     * @throws URISyntaxException
     * @throws IOException 
     */
    public void testIf001() throws URISyntaxException, IOException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();

//        String payloadJson = "{\r\n" + "    \"trflCnfmDvsnCode\" : \"011\",\r\n" + "    \"trflCnfmBswrDvsnCode\" : \"01\",\r\n"
//                + "    \"trflCnfmChnlCode\" : \"01\",\r\n" + "    \"prcsBswrScrnId\" : \"MSESTEP05\",\r\n"
//                + "    \"trflCnfmJobCode\" : \"CS001\",\r\n" + "    \"custId\" : \"0052778800\",\r\n"
//                + "    \"custNm\" : \"최주열\",\r\n" + "    \"isncDate\" : \"\",\r\n" + "    \"btdt\" : \"19881022\",\r\n"
//                + "    \"drvnLcnsSqno\" : \"\",\r\n" + "    \"rrno\" : \"8810221229923\",\r\n"
//                + "    \"drvnLcnsNo\" : \"130760650530\",\r\n" + "    \"frnrRgstNo\" : \"\",\r\n" + "    \"psprNo\" : \"\",\r\n"
//                + "    \"expyDate\" : \"\"\r\n" + "  }";
//
//        IfMcCs001_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs001_I.class);
        
        
        DataHeader dataHeader = new IfMcCs001_I.DataHeader();
        dataHeader.setCRTF_RTCD("");
        dataHeader.setDLRE_MSG("");
        dataHeader.setSCRN_ID(SCRN_ID);
        dataHeader.setSRVC_ID(SRVC_ID);
        dataHeader.setX_OCR_SECRET(X_OCR_SECRET);
        
        
//        String ocrFilePath = "C:\\_\\ocr\\sample_idcard.dat";
//        String ocrFilePath = "C:\\_\\ocr\\sample_driver_license.dat";
//        String ocrFilePath = "C:\\_\\ocr\\sample_residence_card.dat";
        String ocrFilePath = "C:\\_\\ocr\\sample_passport.dat";
        
        File file = new File(ocrFilePath);
        Path path = file.toPath();
        
        List<String> lines = Files.readAllLines(path);
        
        String delimiter = "";
        String ocrData = String.join(delimiter, lines);
        
        String refinedOcrData = ocrData.replaceAll("^data:image/png;base64,", "");
//        String refinedOcrData = ocrData;
        
        logger.debug(refinedOcrData);
        
        Image image = new IfMcCs001_I.DataBody.Image();
        image.setName("test_idcard");
        image.setFormat("png");
        image.setData(refinedOcrData);
        
        List<Image> imageList = new ArrayList<>();
        imageList.add(image);

        DataBody dataBody = new IfMcCs001_I.DataBody();
        dataBody.setImages(imageList);
        dataBody.setORGN_CODE(ORGN_CODE);
        dataBody.setUSER_ID("");
        
        
        IfMcCs001_I inputPayload = new IfMcCs001_I();
        inputPayload.setDataHeader(dataHeader);
        inputPayload.setDataBody(dataBody);
        
        
        
        
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs001;

        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode());
        
        
        IfTelegram<IfMcCs001_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload, IfMcCs001_O.class);
        
        IfMcCs001_O outputPayload = outputTelegram.getPayload();
        
//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
        
    }
    
    
    
    /**
     * 진위확인결과조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf002() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        
        /*
        // custId 있음
        String payloadJson = "{\r\n" + "    \"trflCnfmDvsnCode\" : \"011\",\r\n" + "    \"trflCnfmBswrDvsnCode\" : \"01\",\r\n"
                + "    \"trflCnfmChnlCode\" : \"01\",\r\n" + "    \"prcsBswrScrnId\" : \"MSESTEP05\",\r\n"
                + "    \"trflCnfmJobCode\" : \"CS001\",\r\n" + "    \"custId\" : \"0052778800\",\r\n"
                + "    \"custNm\" : \"최주열\",\r\n" + "    \"isncDate\" : \"\",\r\n" + "    \"btdt\" : \"19881022\",\r\n"
                + "    \"drvnLcnsSqno\" : \"\",\r\n" + "    \"rrno\" : \"8810221229923\",\r\n"
                + "    \"drvnLcnsNo\" : \"130760650530\",\r\n" + "    \"frnrRgstNo\" : \"\",\r\n" + "    \"psprNo\" : \"\",\r\n"
                + "    \"expyDate\" : \"\"\r\n" + "  }";
        */
        /*
        // custId 없음
        String payloadJson = "{\r\n" + "    \"trflCnfmDvsnCode\" : \"011\",\r\n" + "    \"trflCnfmBswrDvsnCode\" : \"01\",\r\n"
                + "    \"trflCnfmChnlCode\" : \"01\",\r\n" + "    \"prcsBswrScrnId\" : \"MSESTEP05\",\r\n"
                + "    \"trflCnfmJobCode\" : \"CS001\",\r\n" + "    \"custId\" : \"\",\r\n"
                + "    \"custNm\" : \"최주열\",\r\n" + "    \"isncDate\" : \"\",\r\n" + "    \"btdt\" : \"19881022\",\r\n"
                + "    \"drvnLcnsSqno\" : \"\",\r\n" + "    \"rrno\" : \"8810221229923\",\r\n"
                + "    \"drvnLcnsNㅓo\" : \"130760650530\",\r\n" + "    \"frnrRgstNo\" : \"\",\r\n" + "    \"psprNo\" : \"\",\r\n"
                + "    \"expyDate\" : \"\"\r\n" + "  }";
        */
        
        
        
        // custId, btdt 없음 >>> 에러
        String payloadJson = "{\r\n" + "    \"trflCnfmDvsnCode\" : \"011\",\r\n" + "    \"trflCnfmBswrDvsnCode\" : \"01\",\r\n"
                + "    \"trflCnfmChnlCode\" : \"01\",\r\n" + "    \"prcsBswrScrnId\" : \"MSESTEP05\",\r\n"
                + "    \"trflCnfmJobCode\" : \"CS001\",\r\n" + "    \"custId\" : \"\",\r\n"
                + "    \"custNm\" : \"최주열11\",\r\n" + "    \"isncDate\" : \"\",\r\n" + "    \"btdt\" : \"\",\r\n"
                + "    \"drvnLcnsSqno\" : \"\",\r\n" + "    \"rrno\" : \"8810221229923\",\r\n"
                + "    \"drvnLcnsNo\" : \"130760650530\",\r\n" + "    \"frnrRgstNo\" : \"\",\r\n" + "    \"psprNo\" : \"\",\r\n"
                + "    \"expyDate\" : \"\"\r\n" + "  }";
        
        
        
        
        IfMcCs002_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs002_I.class);

        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs002;

        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs002_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs002_O.class);

        IfMcCs002_O outputPayload = outputTelegram.getPayload();

//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }

    /**
     * 신분증스캔후처리  
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf003() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        /*
        String payloadJson = "{\r\n" + "    \"csnsYn\": \"Y\",\r\n" + "    \"pushRcvrEmnb\": \"2030205\",\r\n"
                + "    \"custId\": \"1005132571\"\r\n" + "}";
        */
        /*
        String payloadJson = "{\r\n" + "    \"csnsYn\": \"Y\",\r\n" + "    \"pushRcvrEmnb\": \"2030205\",\r\n"
                + "    \"custId\": \"1005132571\"\r\n" + "}";
        */
        

        String payloadJson = "{\r\n" + "    \"csnsYn\": \"Y\",\r\n" + "    \"pushRcvrEmnb\": \"2030205\",\r\n"
                + "    \"custId\": \"1005132571\"\r\n" + "}";
        
        
        IfMcCs003_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs003_I.class);
        
        inputPayload.setPushRcvrEmnb(EMNB); // 사원번호 overwrite
        
//        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, "10777777", ACTIVE_PROFILE, IF_ENDPOINT_URL);
        
        

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs003;

        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs003_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs003_O.class);

        IfMcCs003_O outputPayload = outputTelegram.getPayload();

//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }

    /**
     * SSO대체로그인인증 
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf005() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        
        /*
        String payloadJson = "{\r\n" + 
                "    \"emnb\": \"1077593\",\r\n" + 
                "    \"lognPswd\": \"hlihli1!\"\r\n" + 
                "}";
        */
        
        
        String payloadJson = "{\r\n" + 
                "    \"emnb\": \"1077593\",\r\n" + 
                "    \"lognPswd\": \"hlihli2@1\"\r\n" + 
                "}";
        
        /*
        String payloadJson = "{\r\n" + 
                "    \"emnb\": \"1077593\",\r\n" + 
                "    \"lognPswd\": \"hlihli2@\"\r\n" + 
                "}";
        */
        /*
        String payloadJson = "{\r\n" + 
                "    \"emnb\": \"1077593\",\r\n" + 
                "    \"lognPswd\": \"!Redcamel4348h\"\r\n" + 
                "}";
        */
        /*
        String payloadJson = "{\r\n" + "    \"emnb\" : \"1077739\",\r\n"
                + "    \"lognPswd\" : \"EixO7RZy6bW7Wa.qxs1qs6mfF4e81tGf\"\r\n" 
                + "  }";
        */
        /*
        String payloadJson = "{\r\n" + 
                "    \"emnb\" : \"1021814\",\r\n" + 
                "    \"lognPswd\" : \"8cFbvEcq.7DsTqDQsTbMCxYPvC15bsmn\"\r\n" + 
                "  }";
        */
        
        
        IfMcCs005_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs005_I.class);
		String emnb = inputPayload.getEmnb();
        String lognPswd = inputPayload.getLognPswd();
        String encString = SHA256CmCrypt.SHA256_getEncString(lognPswd);
        
        inputPayload.setLognPswd(encString);
        
        System.out.println("========== SHA256CmCrypt.getEncString(lognPswd) ===================");
        System.out.println("emnb : " + emnb);
        System.out.println("lognPswd : " + lognPswd);
        System.out.println("encString : " + inputPayload.getLognPswd());
        
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs005;

        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs005_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs005_O.class);

        IfMcCs005_O outputPayload = outputTelegram.getPayload();

//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }

    /**
     * 사원목록조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf006() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        
        String payloadJson = "{\r\n" + "    \"scwdNm\" : \"1871261\",\r\n" + "    \"orgnCode\" : \"\",\r\n"
                + "    \"empeDvsnCode\" : \"\",\r\n" + "    \"wholEmpeInqyYn\" : \"N\",\r\n" + "    \"fpExlsYn\" : \"N\",\r\n"
                + "    \"tnofDvsnCode\" : \"\"\r\n" + "  }";
        
        /*
        String payloadJson = "{\r\n" + "    \"scwdNm\" : \"9999999\",\r\n" + "    \"orgnCode\" : \"\",\r\n"
                + "    \"empeDvsnCode\" : \"AA\",\r\n" + "    \"wholEmpeInqyYn\" : \"N\",\r\n" + "    \"fpExlsYn\" : \"N\",\r\n"
                + "    \"tnofDvsnCode\" : \"\"\r\n" + "  }";
        */
        IfMcCs006_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs006_I.class);

        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs006;

        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs006_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs006_O.class);

        IfMcCs006_O outputPayload = outputTelegram.getPayload();

//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }

    /**
     * 고객계약정보조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    
    public void testIf007() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        /*
        String payloadJson = "{\r\n" + 
                "    \"custId\" : \"8061129138\",\r\n" + 
                "    \"polyNo\" : \"\",\r\n" + 
                "    \"cntcDvsnCode\" : \"01\",\r\n" + 
                "    \"custDvsnCode\" : \"01\",\r\n" + 
                "    \"nextKey\" : \"1\",\r\n" + 
                "    \"pageSize\" : 50\r\n" + 
                "  }";
        */
        
        String payloadJson = "{\r\n" + 
                "    \"custId\" : \"8031662218\",\r\n" + 
                "    \"polyNo\" : \"\",\r\n" + 
                "    \"cntcDvsnCode\" : \"01\",\r\n" + 
                "    \"custDvsnCode\" : \"01\",\r\n" + 
                "    \"nextKey\" : \"1\",\r\n" + 
                "    \"pageSize\" : 50\r\n" + 
                "  }";
        
        
        
        /*
        String payloadJson = "{\r\n" + 
                "    \"polyNo\": \"\",\r\n" + 
                "    \"cntcDvsnCode\": \"01\",\r\n" + 
                "    \"custId\": \"2054672042\",\r\n" + 
                "    \"custDvsnCode\": \"01\",\r\n" + 
                "    \"nextKey\": \"1\",\r\n" + 
                "    \"pageSize\": 50\r\n" + 
                "}";
        */
        /*
        String payloadJson = "{\r\n" + 
                "    \"polyNo\": \"\",\r\n" + 
                "    \"cntcDvsnCode\": \"01\",\r\n" + 
                "    \"custId\": \"2054672042\",\r\n" + 
                "    \"custDvsnCode\": \"01\"\r\n" + 
                "}";
        */
    
        /*
        String payloadJson = "{\r\n" + 
                "        \"custId\": \"0005593960\",\r\n" + 
                "        \"polyNo\": \"\",\r\n" + 
                "        \"cntcDvsnCode\": \"01\",\r\n" + 
                "        \"custDvsnCode\": \"01\",\r\n" + 
                "        \"nextKey\": \"1\",\r\n" + 
                "        \"pageSize\": 50\r\n" + 
                "    }";
        */
        
        IfMcCs007_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs007_I.class);
        System.out.println("---------- " +OBJECT_MAPPER.writeValueAsString(inputPayload));
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs007;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs007_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs007_O.class);
    
        IfMcCs007_O outputPayload = outputTelegram.getPayload();
    
//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }
    
    /**
     * 고객계좌목록조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    
    public void testIf008() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        
        /*
        String payloadJson = "{\r\n" + 
                "    \"custId\" : \"6068807976\",\r\n" + 
                "    \"pageSize\" : 20,\r\n" + 
                "    \"pageNumber\" : 1\r\n" + 
                "  }";
        */
        /* 
        String payloadJson = "{\r\n" + 
                "    \"custId\": \"6068807976\"\r\n" + 
                "}";
        */
        /*
        String payloadJson = "{\r\n" + 
                "    \"custId\": \"606880797612345\"\r\n" + 
                "}";
        */
         
        String payloadJson = "{\r\n" + 
                "    \"custId\": \"8031662218\"\r\n" + 
                "}";
        
        IfMcCs008_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs008_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
//        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, "10777777", ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs008;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs008_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs008_O.class);
    
        IfMcCs008_O outputPayload = outputTelegram.getPayload();
    
//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }
    
    
    /**
     * 개인정보유출노출여부조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf009() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        /*
        String payloadJson = "{\r\n" + 
                "    \"custId\": \"3002220133\"\r\n" + 
                "}";
        */
        
        String payloadJson = "{\r\n" + 
                "    \"custId\": \"300222013312345\"\r\n" + 
                "}";
        
        IfMcCs009_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs009_I.class);

        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs009;

        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs009_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs009_O.class);

        IfMcCs009_O outputPayload = outputTelegram.getPayload();

//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }

    
    
    /**
     * 간편인증 토큰발급
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf010() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        /*
        String payloadJson = "{\r\n" + 
                "    \"dataHeader\": {\r\n" + 
                "        \"SRVC_ID\": \"SVC028\",\r\n" + 
                "        \"SCRN_ID\": \"화면 ID\",\r\n" + 
                "        \"CRTF_RTCD\": \"\",\r\n" +
                "        \"DLRE_MSG\": \"\",\r\n" + 
                "        \"ORGN_CODE\": \"00630\",\r\n" + 
                "        \"USER_ID\": \"USER_001\"\r\n" + 
                "    },\r\n" + 
                "    \"dataBody\": {\r\n" + 
                "        \"grant_type\": \"client_credentials\"\r\n" + 
                "    }\r\n" + 
                "}";
        */
        
        String payloadJson = "{\r\n" + 
                "    \"dataHeader\": {\r\n" + 
                "        \"SRVC_ID\": \"SVC028\",\r\n" + 
                "        \"SCRN_ID\": \"화면 ID\",\r\n" + 
                "        \"CRTF_RTCD\": \"\",\r\n" +
                "        \"DLRE_MSG\": \"\",\r\n" + 
                "        \"ORGN_CODE\": \"00630\",\r\n" + 
                "        \"USER_ID\": \"USER_001\"\r\n" + 
                "    },\r\n" + 
                "    \"dataBody\": {\r\n" + 
                "        \"grant_type\": \"client_credentials12345\"\r\n" + 
                "    }\r\n" + 
                "}";
        
        IfMcCs010_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs010_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs010;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs010_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs010_O.class);
    
        IfMcCs010_O outputPayload = outputTelegram.getPayload();
    
//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }
    
    
    
    
    
    /**
     * 간편인증 요청
     * @throws JsonProcessingException
     * @throws URISyntaxException
     * @throws InvalidAlgorithmParameterException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     */
	
	public void testIf011() throws JsonProcessingException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
	    String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
	
	    String payloadJson_dataHeader = "{\r\n" + 
	    		"    \"SRVC_ID\": \"SVC028\",\r\n" + 
	    		"    \"SCRN_ID\": \"화면 ID\",\r\n" + 
	    		"    \"CRTF_RTCD\": \"\",\r\n" + 
	    		"    \"DLRE_MSG\": \"\",\r\n" + 
	    		"    \"ORGN_CODE\": \"00630\",\r\n" + 
	    		"    \"USER_ID\": \"USER_001\"\r\n" + 
	    		"}";
	    IfMcCs011_I.DataHeader dataHeader = OBJECT_MAPPER.readValue(payloadJson_dataHeader, IfMcCs011_I.DataHeader.class);
	
	    
	    
	    IfMcCs011_I.DataBody dataBody = new IfMcCs011_I.DataBody();
	    
	    String token_type = TOKEN_TYPE;
//	    String access_token = INITECH_O_AUTH_TOKEN;
	    String access_token = "1111111111111111111";
	    
	    String initechOAuthToken = token_type + " " + access_token;
	    dataBody.setInitechOAuthToken(initechOAuthToken);
	    
	    String ezCertSrvcId = IfConstant.EzCertSrvcId.EZ_KAKAOV2.getValue();
	    dataBody.setPid(ezCertSrvcId);
	    
	    String name = "신용진";
	    String uname = AesUtil.encrypt(name, AES_KEY, AES_IV);
	    dataBody.setUname(uname);
	    
	    String birthday = "791015";
	    String ubirthday = AesUtil.encrypt(birthday, AES_KEY, AES_IV);
	    dataBody.setUbirthday(ubirthday);
	    
	    String gender = "1";
	    String ugender = AesUtil.encrypt(gender, AES_KEY, AES_IV);
	    dataBody.setUgender(ugender);
	    
	    String phone = "01028893661";
	    String uphone = AesUtil.encrypt(phone, AES_KEY, AES_IV);
	    dataBody.setUphone(uphone);
	    
	    String op = IfConstant.EzCertSrvcOp.auth.getValue();
	    dataBody.setOp(op);
	    
	    IfMcCs011_I.DataBody.Sign sign = new IfMcCs011_I.DataBody.Sign();
	    sign.setContents("");
	    dataBody.setSign(sign);
	    
	    String deviceCode = IfConstant.EzCertSrvcDeviceCode.MOBILE.getValue();
		dataBody.setDeviceCode(deviceCode);
	    
	    String deviceBrowser = IfConstant.EzCertSrvcDeviceBrowser.webBrowser.getValue();
		dataBody.setDeviceBrowser(deviceBrowser);;
	    
	    Callback callback = new IfMcCs011_I.DataBody.Callback();
	    callback.setFailCallbackUrl("");
	    callback.setMobileOs("");
	    callback.setSuccessCallbackUrl("");
	    callback.setTelcoTycd("");
	    dataBody.setCallback(callback);
	    
	    dataBody.setChannel("");
	    
	    
	    
	    IfMcCs011_I ifMcCs011_I = new IfMcCs011_I();
	    ifMcCs011_I.setDataHeader(dataHeader);
	    ifMcCs011_I.setDataBody(dataBody);
	    
	    String payloadJson = OBJECT_MAPPER.writeValueAsString(ifMcCs011_I);
	    IfMcCs011_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs011_I.class);
	
	    IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
	    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs011;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs011_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs011_O.class);
    
        IfMcCs011_O outputPayload = outputTelegram.getPayload();
    
//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
	}
	
    
    
    
    /**
     * 간편인증 상태 조회 
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf012() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
    
        
        String payloadJson_dataHeader = "{\r\n" + 
	    		"    \"SRVC_ID\": \"SVC028\",\r\n" + 
	    		"    \"SCRN_ID\": \"화면 ID\",\r\n" + 
	    		"    \"CRTF_RTCD\": \"\",\r\n" + 
	    		"    \"DLRE_MSG\": \"\",\r\n" + 
	    		"    \"ORGN_CODE\": \"00630\",\r\n" + 
	    		"    \"USER_ID\": \"USER_001\"\r\n" + 
	    		"}";
	    com.gooroomee.backbone.external.dto.intrf.IfMcCs012_I.DataHeader dataHeader = OBJECT_MAPPER.readValue(payloadJson_dataHeader, com.gooroomee.backbone.external.dto.intrf.IfMcCs012_I.DataHeader.class);
	
	    
	    
	    com.gooroomee.backbone.external.dto.intrf.IfMcCs012_I.DataBody dataBody = new IfMcCs012_I.DataBody();
	    
	    
	    // XXX
	    String token_type = TOKEN_TYPE;
        String access_token = INITECH_O_AUTH_TOKEN;
        String initechOAuthToken = token_type + " " + access_token;
		dataBody.setInitechOAuthToken(initechOAuthToken);
		
		// XXX
		String reqTxId = REQ_TX_ID;
		dataBody.setReqTxId(reqTxId);

		String op = IfConstant.EzCertSrvcOp.auth.getValue();
		dataBody.setOp(op);
        
		
		IfMcCs012_I ifMcCs012_I = new IfMcCs012_I();
		ifMcCs012_I.setDataHeader(dataHeader);
		ifMcCs012_I.setDataBody(dataBody);
	    
	    String payloadJson = OBJECT_MAPPER.writeValueAsString(ifMcCs012_I);
	    IfMcCs012_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs012_I.class);
    
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs012;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs012_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs012_O.class);
    
        IfMcCs012_O outputPayload = outputTelegram.getPayload();
    
//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }
    
    
    
    
    /**
     * 고객통합기본정보조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf013() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        /*
        String payloadJson = "{\r\n" + 
                "    \"scwdNm\" : \"가로공원로 82길\"\r\n" + 
                "  }";
        */
        
        String payloadJson = "{\r\n" + 
                "    \"inqySrchCndtVal\" : \"\",\r\n" + 
                "    \"custId\" : \"9026323439\",\r\n" + 
                "    \"polyNo\" : \"\",\r\n" + 
                "    \"rrno\" : \"\"\r\n" + 
                "  }";
        
        IfMcCs013_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs013_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs013;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs013_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload, IfMcCs013_O.class);
    
        IfMcCs013_O outputPayload = outputTelegram.getPayload();
    
//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }
    
    
    
    
    
    
    /**
     * SMS 메세지 발송
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf014() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        /*
        String payloadJson = "{\r\n" + 
                "    \"scwdNm\" : \"가로공원로 82길\"\r\n" + 
                "  }";
        */
        /*
        String payloadJson = "{\r\n" + 
                "    \"sendCont\" : 1,\r\n" + 
                "    \"jobMsgeCntn\" : \"FP월드 인증번호 [601568] 를 입력해주세요!\r\n" + 
                "<스마트인증 앱>\r\n" + 
                "https://goo.gl/wF26wx\",\r\n" + 
                "    \"sendEmnb\" : \"1180398\",\r\n" + 
                "    \"sendEmpeNm\" : \"영업포탈\",\r\n" + 
                "    \"sndeDeptCode\" : \"00398\",\r\n" + 
                "    \"sndeDeptNm\" : \"영업포탈\",\r\n" + 
                "    \"belnOrgnCode\" : \"00398\",\r\n" + 
                "    \"nttkTmplNm\" : \"SYZZ001004\",\r\n" + 
                "    \"trnnPrgmId\" : \"uvpmn010mvw\",\r\n" + 
                "    \"bswrNm\" : \"FP월드 인증번호 발송\",\r\n" + 
                "    \"rcveCnfmYn\" : \"Y\",\r\n" + 
                "    \"custId\" : \"0000099995\",\r\n" + 
                "    \"hpTlphSbno\" : \"mLCh\",\r\n" + 
                "    \"hpTlphOfno\" : \"4167\",\r\n" + 
                "    \"hpTlphTlcmNo\" : \"010\",\r\n" + 
                "    \"sndeTlphArcd\" : \"010\",\r\n" + 
                "    \"sndeTlphOfno\" : \"4167\",\r\n" + 
                "    \"sndeTlphInno\" : \"mLCh\",\r\n" + 
                "    \"ntfcKindCode\" : \"ZAC9002\",\r\n" + 
                "    \"rcvrNm\" : \"안광직\"\r\n" + 
                "  }";
        */
        
        String payloadJson = "{\r\n" + 
                "    \"sendCont\": 1,\r\n" + 
                "    \"jobMsgeCntn\": \"인증번호 999를 입력해 주세요.\",\r\n" + 
                "    \"sendEmnb\": \"1077593\",\r\n" + 
                "    \"sendEmpeNm\": \"홍길동\",\r\n" + 
                "    \"sndeDeptCode\": \"00630\",\r\n" + 
                "    \"sndeDeptNm\": \"보험서비스팀\",\r\n" + 
                "    \"belnOrgnCode\": \"00630\",\r\n" + 
                "    \"nttkTmplNm\": \"SZAU000002\",\r\n" + 
                "    \"trnnPrgmId\": \"111\",\r\n" + 
                "    \"bswrNm\": \"모바일 화상상담 서비스 문자인증\",\r\n" + 
                "    \"rcveCnfmYn\": \"Y\",\r\n" + 
                "    \"custId\": \"0000099995\",\r\n" + 
                "    \"hpTlphSbno\": \"3661\",\r\n" + 
                "    \"hpTlphOfno\": \"2889\",\r\n" + 
                "    \"hpTlphTlcmNo\": \"010\",\r\n" + 
                "    \"sndeTlphArcd\": \"\",\r\n" + 
                "    \"sndeTlphOfno\": \"1588\",\r\n" + 
                "    \"sndeTlphInno\": \"6363\",\r\n" + 
                "    \"ntfcKindCode\": \"ZAU0008\",\r\n" + 
                "    \"rcvrNm\": \"임꺽정\"\r\n" + 
                "}";
        
        
        IfMcCs014_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs014_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs014;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs014_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload, IfMcCs014_O.class);
    
        IfMcCs014_O outputPayload = outputTelegram.getPayload();
    
//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }
    
    
    
    
    
    
    
    /**
     * 알림톡전송
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf015_1() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        /*
        String payloadJson = "{\r\n" + 
                "    \"sendOrchOrgnCode\": \"00000\",\r\n" + 
                "    \"aentEmnb\": \"22311515\",\r\n" + 
                "    \"msgeInptYn\": \"Y\",\r\n" + 
                "    \"tlphSbno\": \"****\",\r\n" + 
                "    \"cltnOrgnCode\": \"13017\",\r\n" + 
                "    \"tlphOfno\": \"3974\",\r\n" + 
                "    \"rqsrEmnb\": \"22226213\",\r\n" + 
                "    \"rfrnBwno\": \"1\",\r\n" + 
                "    \"isneTypeDvsnCode\": \"2\",\r\n" + 
                "    \"custId\": \"8027263038\",\r\n" + 
                "    \"ntfcRfdt\": \"20231025\",\r\n" + 
                "    \"actlDatYn\": \"Y\",\r\n" + 
                "    \"fileRqstCont\": 0,\r\n" + 
                "    \"custNm\": \"장동준\",\r\n" + 
                "    \"sndeTlphOfno\": \"1588\",\r\n" + 
                "    \"ntfcKindCode\": \"ZZZ9001\",\r\n" + 
                "    \"rpntMvmnTlphCnplSuid\": 9300000000005563838,\r\n" + 
                "    \"stndCrnyDcmlPsitCnt\": 0,\r\n" + 
                "    \"mgmtNoDvsnCode\": \"5\",\r\n" + 
                "    \"ntfcTmplCode\": \"AMA00013\",\r\n" + 
                "    \"tlphIdnfCode\": \"010\",\r\n" + 
                "    \"sndeTlphInno\": \"****\",\r\n" + 
                "    \"sendOrchEmnb\": \"MCI0001\",\r\n" + 
                "    \"rrno\": \"*************\",\r\n" + 
                "    \"msgeTitlNm\": \"정보조회동의 처리 결과 안내\",\r\n" + 
                "    \"msgeCntn\": \"장동준 고객님 안녕하십니까?\\\\n한화생명은 고객님의 소중한 정보보호를 위해\\\\n당사(케이금융파트너스서울지점 장선미FP)에 정보처리동의를 하신\\\\n고객님들께 동의에 대한 확인 및 감사 알림톡을 발송하고 있습니다.\\\\n소중한 고객님의 정보가 체계적으로 관리되도록 최선을 다하겠습니다.\\\\n항상 한화생명을 아껴주셔서 감사합니다.\\\\n궁금하신 사항은 지점 02-499-8531 또는 콜센터 1588-6363으로\\\\n문의 가능하십니다.\\\\n오늘도 좋은 하루 되십시요. 감사합니다.\\\\n\"\r\n" + 
                "}";
        */
        
        String payloadJson = "{\r\n" + 
                "    \"sendOrchOrgnCode\": \"00630\",\r\n" + 
//                "    \"aentEmnb\": \"1940437\",\r\n" +
                "    \"aentEmnb\": \"1077593\",\r\n" + 
                "    \"msgeInptYn\": \"N\",\r\n" + 
                "    \"ntfcKindCode\": \"SVP0412\",\r\n" + 
                "    \"rpntMvmnTlphCnplSuid\": 9300000000015234179,\r\n" + 
                "    \"sendItmList\": [\r\n" + 
                "        {\r\n" + 
                "            \"umsItmVal\": \"보험계약대출 지급완료안내\",\r\n" + 
                "            \"sqnb\": 1\r\n" + 
                "        },\r\n" + 
                "        {\r\n" + 
                "            \"umsItmVal\": \"김수정\",\r\n" + 
                "            \"sqnb\": 2\r\n" + 
                "        },\r\n" + 
                "        {\r\n" + 
                "            \"umsItmVal\": \"대출 지급완료를\",\r\n" + 
                "            \"sqnb\": 3\r\n" + 
                "        },\r\n" + 
                "        {\r\n" + 
                "            \"umsItmVal\": \"2023-10-30\",\r\n" + 
                "            \"sqnb\": 4\r\n" + 
                "        },\r\n" + 
                "        {\r\n" + 
                "            \"umsItmVal\": \"201824611\",\r\n" + 
                "            \"sqnb\": 5\r\n" + 
                "        },\r\n" + 
                "        {\r\n" + 
                "            \"umsItmVal\": \"한화생명 e재테크 저축보험 무배당\",\r\n" + 
                "            \"sqnb\": 6\r\n" + 
                "        },\r\n" + 
                "        {\r\n" + 
                "            \"umsItmVal\": \"200,000\",\r\n" + 
                "            \"sqnb\": 7\r\n" + 
                "        },\r\n" + 
                "        {\r\n" + 
                "            \"umsItmVal\": \"■ 실수령금 : 200,000원\",\r\n" + 
                "            \"sqnb\": 8\r\n" + 
                "        },\r\n" + 
                "        {\r\n" + 
                "            \"umsItmVal\": \"국민은행\",\r\n" + 
                "            \"sqnb\": 9\r\n" + 
                "        },\r\n" + 
                "        {\r\n" + 
                "            \"umsItmVal\": \"4509010******3\",\r\n" + 
                "            \"sqnb\": 10\r\n" + 
                "        },\r\n" + 
                "        {\r\n" + 
                "            \"umsItmVal\": \"변동형\",\r\n" + 
                "            \"sqnb\": 11\r\n" + 
                "        },\r\n" + 
                "        {\r\n" + 
                "            \"umsItmVal\": \"4.25\",\r\n" + 
                "            \"sqnb\": 12\r\n" + 
                "        },\r\n" + 
                "        {\r\n" + 
                "            \"umsItmVal\": \"2023-10-30\",\r\n" + 
                "            \"sqnb\": 13\r\n" + 
                "        },\r\n" + 
                "        {\r\n" + 
                "            \"umsItmVal\": \"2033-08-23\",\r\n" + 
                "            \"sqnb\": 14\r\n" + 
                "        }\r\n" + 
                "    ],\r\n" + 
                "    \"stndCrnyDcmlPsitCnt\": 0,\r\n" + 
                "    \"sbsnMsgeCntn\": \"\",\r\n" + 
                "    \"cltnOrgnCode\": \"08565\",\r\n" + 
                "    \"mgmtNoDvsnCode\": \"5\",\r\n" + 
                "    \"ntfcTmplCode\": \"ASVP000008\",\r\n" + 
                "    \"rqsrEmnb\": \"1484752\",\r\n" + 
                "    \"rfrnBwno\": \"1029256771\",\r\n" + 
                "    \"sendOrchEmnb\": \"1484752\",\r\n" + 
                "    \"isneTypeDvsnCode\": \"1\",\r\n" + 
                "    \"msgeTitlNm\": \"보험계약대출 신청 및 지급완료 안내\",\r\n" + 
                "    \"msgeCntn\": \"\",\r\n" + 
                "    \"custId\": \"1029256771\",\r\n" + 
                "    \"ntfcRfdt\": \"20231030\",\r\n" + 
                "    \"actlDatYn\": \"N\",\r\n" + 
                "    \"crnyCode\": \"KRW\",\r\n" + 
                "    \"fileRqstCont\": 0\r\n" + 
                "}";
        
    
        IfMcCs015_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs015_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
        
//        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs015_1;
        // XXX
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs015;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs015_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs015_O.class);
    
        IfMcCs015_O outputPayload = outputTelegram.getPayload();
    
//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }
    
    
    
    /**
     * 카카오알림톡발송_쳇버블
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf015_2() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        
//        [tmplCode]
//        FNC9001
//        INM0009
//        INM9001 
        
        
        /*
        String payloadJson = "{\r\n" + 
                "    \"hpTlphSbno\": \"3661\",\r\n" + 
                "    \"tmplCode\": \"FNC9001\",\r\n" + 
                "    \"sndeTlno\": \"\",\r\n" + 
                "    \"dutySendYn\": \"Y\",\r\n" + 
                "    \"sndeDeptCode\": \"00320\",\r\n" + 
                "    \"sendCont\": 1,\r\n" + 
                "    \"btchPrcsYn\": \"1\",\r\n" + 
                "    \"hpTlphTlcmNo\": \"010\",\r\n" + 
                "    \"sbsnSendMsgeCntn\": \"최진원님 안녕하세요\\r\\n저희 한화생명보험 대출상품을 문의해 주셔서 감사합니다.\\r\\n「금융소비자보호에 관한 법률」 및 동 법 시행령에 따라 고객님이 문의하신 대출상품의 적합성\\/적정성 여부를 판단하기 위해 당사에 제공한 정보는 다음과 같습니다.\\r\\n\\r\\n■ [제공정보]\\r\\n취약금융소비자 구분: 해당없음\\r\\n대출용도: 주택자금\\r\\n변제계획: 근로소득\\r\\n연간소득: 5천만~1억원 미만\\r\\n부채: 1천만 미만\\r\\n고정지출: 1백만 미만\\r\\n신용점수: 800점 이상\\r\\n\\r\\n\\u203B제공하신 정보 및 기타 대출관련 문의사항은 한화생명 콜센터(☎ 1588-6363)을 이용해 주시기 바랍니다.\\r\\n감사합니다.\",\r\n" + 
                "    \"nttkButnCntn\": \"Y\",\r\n" + 
                "    \"custId\": \"1025835861\",\r\n" + 
                "    \"hpTlphOfno\": \"2889\",\r\n" + 
                "    \"rcvrTlno\": \"\",\r\n" + 
                "    \"sndeTlphArcd\": \"\",\r\n" + 
                "    \"sndeTlphOfno\": \"1588\",\r\n" + 
                "    \"ntfcKindCode\": \"FNC9001\",\r\n" + 
                "    \"trnnPrgmId\": \"SVfnact146in\",\r\n" + 
                "    \"sbsnSendYn\": \"Y\",\r\n" + 
                "    \"ntfcTmplCode\": \"AFNC000008\",\r\n" + 
                "    \"jobMsgeCntn\": \"최진원님 안녕하세요\\r\\n저희 한화생명보험 대출상품을 문의해 주셔서 감사합니다.\\r\\n「금융소비자보호에 관한 법률」 및 동 법 시행령에 따라 고객님이 문의하신 대출상품의 적합성\\/적정성 여부를 판단하기 위해 당사에 제공한 정보는 다음과 같습니다.\\r\\n\\r\\n■ [제공정보]\\r\\n취약금융소비자 구분: 해당없음\\r\\n대출용도: 주택자금\\r\\n변제계획: 근로소득\\r\\n연간소득: 5천만~1억원 미만\\r\\n부채: 1천만 미만\\r\\n고정지출: 1백만 미만\\r\\n신용점수: 800점 이상\\r\\n\\r\\n\\u203B제공하신 정보 및 기타 대출관련 문의사항은 한화생명 콜센터(☎ 1588-6363)을 이용해 주시기 바랍니다.\\r\\n감사합니다.\",\r\n" + 
                "    \"onlnBtchDvsnCode\": \"R\",\r\n" + 
                "    \"sndeTlphInno\": \"****\",\r\n" + 
                "    \"msgeTitlNm\": \"적정성\\/적합성 평가 결과안내\",\r\n" + 
                "    \"butnDvsnCode\": \"Y\",\r\n" + 
                "    \"ntfcMdiaDvsnCode\": \"LMS\",\r\n" + 
                "    \"rcvrNm\": \"\"\r\n" + 
                "}";
        */ 
        
        /*
        // [템플릿명]
              모바일 화상상담 서비스 사용 안내
        */
        
        /*
        // [템플릿내용]
        "[한화생명] 모바일 화상상담 서비스 사용 안내\r\n" + 
        "\r\n" + 
        "#{고객명} 고객님 안녕하세요. \r\n" + 
        "모바일 화상상담 서비스를 신청해 주셔서 감사합니다. \r\n" + 
        "하단의 '바로가기' 버튼을 클릭 해 주시면 모바일 화상상담 서비스가 시작됩니다. \r\n" + 
        "\r\n" + 
        "서비스 연결은 메세지를 받으신 후 30분 간 유효함을 안내 드립니다. \r\n" + 
        "감사합니다.\r\n" + 
        "\r\n" + 
        "* 서비스 연결 시 5G/LTE 환경에서 데이터 통화료가 발생할 수 있습니다.";
        */
        
//        "[한화생명] 모바일 화상상담 서비스 사용 안내\r\n\r\n신용진 고객님 안녕하세요. \r\n모바일 화상상담 서비스를 신청해 주셔서 감사합니다. \r\n하단의 '바로가기' 버튼을 클릭 해 주시면 모바일 화상상담 서비스가 시작됩니다. \r\n\r\n서비스 연결은 메세지를 받으신 후 30분 간 유효함을 안내 드립니다. \r\n감사합니다.\r\n\r\n* 서비스 연결 시 5G/LTE 환경에서 데이터 통화료가 발생할 수 있습니다."
        
        /*
        String payloadJson = "{\r\n" + 
                "    \"hpTlphSbno\": \"3661\",\r\n" + 
                "    \"tmplCode\": \"INM9001\",\r\n" + 
                "    \"sndeTlno\": \"\",\r\n" + 
                "    \"dutySendYn\": \"Y\",\r\n" + 
                "    \"sndeDeptCode\": \"00630\",\r\n" + 
                "    \"sendCont\": 1,\r\n" + 
                "    \"btchPrcsYn\": \"1\",\r\n" + 
                "    \"hpTlphTlcmNo\": \"010\",\r\n" + 
                "    \"sbsnSendMsgeCntn\": \"\",\r\n" + 
                "    \"nttkButnCntn\": \"Y\",\r\n" + 
                "    \"custId\": \"1025835861\",\r\n" + 
                "    \"hpTlphOfno\": \"2889\",\r\n" + 
                "    \"rcvrTlno\": \"\",\r\n" + 
                "    \"sndeTlphArcd\": \"[한화생명] 모바일 화상상담 서비스 사용 안내\\r\\n\\r\\n신용진 고객님 안녕하세요. \\r\\n모바일 화상상담 서비스를 신청해 주셔서 감사합니다. \\r\\n하단의 '바로가기' 버튼을 클릭 해 주시면 모바일 화상상담 서비스가 시작됩니다. \\r\\n\\r\\n서비스 연결은 메세지를 받으신 후 30분 간 유효함을 안내 드립니다. \\r\\n감사합니다.\\r\\n\\r\\n* 서비스 연결 시 5G/LTE 환경에서 데이터 통화료가 발생할 수 있습니다.\",\r\n" + 
                "    \"sndeTlphOfno\": \"1588\",\r\n" + 
                "    \"ntfcKindCode\": \"INM9001\",\r\n" + 
                "    \"trnnPrgmId\": \"SVfnact146in\",\r\n" + 
                "    \"sbsnSendYn\": \"Y\",\r\n" + 
                "    \"ntfcTmplCode\": \"AZAU000001\",\r\n" + 
                "    \"jobMsgeCntn\": \"[한화생명] 모바일 화상상담 서비스 사용 안내\\r\\n\\r\\n신용진 고객님 안녕하세요. \\r\\n모바일 화상상담 서비스를 신청해 주셔서 감사합니다. \\r\\n하단의 '바로가기' 버튼을 클릭 해 주시면 모바일 화상상담 서비스가 시작됩니다. \\r\\n\\r\\n서비스 연결은 메세지를 받으신 후 30분 간 유효함을 안내 드립니다. \\r\\n감사합니다.\\r\\n\\r\\n* 서비스 연결 시 5G/LTE 환경에서 데이터 통화료가 발생할 수 있습니다.\",\r\n" + 
                "    \"onlnBtchDvsnCode\": \"R\",\r\n" + 
                "    \"sndeTlphInno\": \"****\",\r\n" + 
                "    \"msgeTitlNm\": \"모바일 화상상담 서비스 사용 안내\",\r\n" + 
                "    \"butnDvsnCode\": \"Y\",\r\n" + 
                "    \"ntfcMdiaDvsnCode\": \"LMS\",\r\n" + 
                "    \"rcvrNm\": \"\"\r\n" + 
                "}";
        */ 
        /*
        String payloadJson = "{\r\n" + 
                "        \"btchPrcsYn\":\"1\",\r\n" + 
                "        \"butnDvsnCode\":\"\",\r\n" + 
                "        \"custId\":\"5019808635\",\r\n" + 
                "        \"dutySendYn\":\"Y\",\r\n" + 
                "        \"hpTlphOfno\":\"2800\",\r\n" + 
                "        \"hpTlphSbno\":\"6889\",\r\n" + 
                "        \"hpTlphTlcmNo\":\"010\",\r\n" + 
                "        \"jobMsgeCntn\":\"[한화생명] 보험 계약자 변경 접수 안내\\n\\n윤회자 고객님 안녕하세요.\\n한화생명 앱을 통해 요청하신 계약자 변경이 아래와 같이 접수 되었습니다.\\n\\n□  증권번호 : 1****8646\\n□  상품명  :   (무)트리플케어종신\\n□  변경 사항  : \\n  변경 전 계약자 및 만기(생존)수익자: 윤회자\\n  변경 후 계약자 및 만기(생존)수익자: 심인순\\n\\n※ 변경 후 계약자 심인순님의 동의가 바로 진행될 수 있도록 안내해 주세요.\\n\\n※ 변경 후 계약자의 동의는 당일 23시 30분 까지만 가능 합니다.(미동의 시 자동 취소)\\n\\n※ 본인이 신청 하지 않은 경우 한화생명 콜센터(1588-6363)로 즉시 고객 정보 유출 신고를 진행하시기 바랍니다.\\n \\n※ 기타 궁금하신 사항은 한화생명 콜센터(1588-6363)로 문의 주시면 친절히 안내해 드리겠습니다.\",\r\n" + 
                "        \"msgeTitlNm\":\"[계약변경] 한화생명 앱 계약자 변경접수(만기수익자 변경시)\",\r\n" + 
                "        \"ntfcKindCode\":\"CTC0020\",\r\n" + 
                "        \"ntfcMdiaDvsnCode\":\"MMS\",\r\n" + 
                "        \"ntfcTmplCode\":\"ACTC000023\",\r\n" + 
                "        \"nttkButnCntn\":\"\",\r\n" + 
                "        \"onlnBtchDvsnCode\":\"R\",\r\n" + 
                "        \"rcvrNm\":\"윤회자\",\r\n" + 
                "        \"rcvrTlno\":\"\",\r\n" + 
                "        \"sbsnSendMsgeCntn\":\"[한화생명] 보험 계약자 변경 접수 안내\\n\\n윤회자 고객님 안녕하세요.\\n한화생명 앱을 통해 요청하신 계약자 변경이 아래와 같이 접수 되었습니다.\\n\\n□  증권번호 : 1****8646\\n□  상품명  :   (무)트리플케어종신\\n□  변경 사항  : \\n  변경 전 계약자 및 만기(생존)수익자: 윤회자\\n  변경 후 계약자 및 만기(생존)수익자: 심인순\\n\\n※ 변경 후 계약자 심인순님의 동의가 바로 진행될 수 있도록 안내해 주세요.\\n\\n※ 변경 후 계약자의 동의는 당일 23시 30분 까지만 가능 합니다.(미동의 시 자동 취소)\\n\\n※ 본인이 신청 하지 않은 경우 한화생명 콜센터(1588-6363)로 즉시 고객 정보 유출 신고를 진행하시기 바랍니다.\\n \\n※ 기타 궁금하신 사항은 한화생명 콜센터(1588-6363)로 문의 주시면 친절히 안내해 드리겠습니다.\",\r\n" + 
                "        \"sbsnSendYn\":\"Y\",\r\n" + 
                "        \"sendCont\":1,\r\n" + 
                "        \"sndeDeptCode\":\"00630\",\r\n" + 
                "        \"sndeTlno\":\"\",\r\n" + 
                "        \"sndeTlphArcd\":\"\",\r\n" + 
                "        \"sndeTlphInno\":\"6363\",\r\n" + 
                "        \"sndeTlphOfno\":\"1588\",\r\n" + 
                "        \"tmplCode\":\"\"\r\n" + 
                "    }";
        */
        /*
        String payloadJson = "{\r\n" + 
                "        \"btchPrcsYn\":\"1\",\r\n" + 
                "        \"butnDvsnCode\":\"2\",\r\n" + 
                "        \"custId\":\"5019808635\",\r\n" + 
                "        \"dutySendYn\":\"Y\",\r\n" + 
                "        \"hpTlphOfno\":\"2889\",\r\n" + 
                "        \"hpTlphSbno\":\"3661\",\r\n" + 
                "        \"hpTlphTlcmNo\":\"010\",\r\n" + 
                "        \"jobMsgeCntn\":\"[한화생명] 모바일 화상상담 서비스 사용 안내\\r\\n\\r\\n신용진 고객님 안녕하세요. \\r\\n모바일 화상상담 서비스를 신청해 주셔서 감사합니다. \\r\\n하단의 '바로가기' 버튼을 클릭 해 주시면 모바일 화상상담 서비스가 시작됩니다. \\r\\n\\r\\n서비스 연결은 메세지를 받으신 후 30분 간 유효함을 안내 드립니다. \\r\\n감사합니다.\\r\\n\\r\\n* 서비스 연결 시 5G/LTE 환경에서 데이터 통화료가 발생할 수 있습니다.\",\r\n" + 
                "        \"msgeTitlNm\":\"모바일 화상상담 서비스 사용 안내\",\r\n" + 
                "        \"ntfcKindCode\":\"ZAU0006\",\r\n" + 
                "        \"ntfcMdiaDvsnCode\":\"LMS\",\r\n" + 
                "        \"ntfcTmplCode\":\"AZAU000001\",\r\n" + 
                "        \"nttkButnCntn\":\"\",\r\n" + 
                "        \"onlnBtchDvsnCode\":\"R\",\r\n" + 
                "        \"rcvrNm\":\"신용진\",\r\n" + 
                "        \"rcvrTlno\":\"\",\r\n" + 
                "        \"sbsnSendMsgeCntn\":\"[한화생명] 모바일 화상상담 서비스 사용 안내\\r\\n\\r\\n신용진 고객님 안녕하세요. \\r\\n모바일 화상상담 서비스를 신청해 주셔서 감사합니다. \\r\\n하단의 '바로가기' 버튼을 클릭 해 주시면 모바일 화상상담 서비스가 시작됩니다. \\r\\n\\\\r\\\\n주소링크 : https://googleg.com \\r\\n\\r\\n서비스 연결은 메세지를 받으신 후 30분 간 유효함을 안내 드립니다. \\r\\n감사합니다.\\r\\n\\r\\n* 서비스 연결 시 5G/LTE 환경에서 데이터 통화료가 발생할 수 있습니다.\",\r\n" + 
                "        \"sbsnSendYn\":\"Y\",\r\n" + 
                "        \"sendCont\":1,\r\n" + 
                "        \"sndeDeptCode\":\"00630\",\r\n" + 
                "        \"sndeTlno\":\"\",\r\n" + 
                "        \"sndeTlphArcd\":\"\",\r\n" + 
                "        \"sndeTlphInno\":\"6363\",\r\n" + 
                "        \"sndeTlphOfno\":\"1588\",\r\n" + 
                "        \"tmplCode\":\"\"\r\n" + 
                "    }";
        */
        
        
        String jobMsgeCntn = "[한화생명] 모바일 화상상담 서비스 사용 안내\r\n" + 
                "\r\n" + 
                "%s 고객님 안녕하세요. \r\n" + 
                "모바일 화상상담 서비스를 신청해 주셔서 감사합니다. \r\n" + 
                "하단의 '바로가기' 버튼을 클릭 해 주시면 모바일 화상상담 서비스가 시작됩니다. \r\n" + 
                "\r\n" + 
                "서비스 연결은 메세지를 받으신 후 30분 간 유효함을 안내 드립니다. \r\n" + 
                "감사합니다.\r\n" + 
                "\r\n" + 
                "* 서비스 연결 시 5G/LTE 환경에서 데이터 통화료가 발생할 수 있습니다.";
        
        String custNm = "신용진";
        
        jobMsgeCntn = String.format(jobMsgeCntn, custNm);
        
        String msgeTitlNm = "모바일 화상상담 서비스 사용 안내";
        
        
        /*
        
        String payloadJson = "{\r\n" + 
                "        \"sendCont\": 1,\r\n" + 
        //                "        \"jobMsgeCntn\": \"[한화생명] 모바일 화상상담 서비스 사용 안내\\r\\n\\r\\n신용진 고객님 안녕하세요. \\r\\n모바일 화상상담 서비스를 신청해 주셔서 감사합니다. \\r\\n하단의 '바로가기' 버튼을 클릭 해 주시면 모바일 화상상담 서비스가 시작됩니다. \\r\\n\\r\\n서비스 연결은 메세지를 받으신 후 30분 간 유효함을 안내 드립니다. \\r\\n감사합니다.\\r\\n\\r\\n* 서비스 연결 시 5G/LTE 환경에서 데이터 통화료가 발생할 수 있습니다.\",\r\n" + 
        //                "        \"jobMsgeCntn\": \"" + a + "\",\r\n" +
                "        \"trnnPrgmId\": null,\r\n" + 
                "        \"sndeDeptCode\": \"00630\",\r\n" + 
                "        \"sendRsvtDttm\": null,\r\n" + 
                "        \"ntfcTmplCode\": \"AZAU000001\",\r\n" + 
                "        \"rcvrTlno\": \"\",\r\n" + 
                "        \"btchPrcsYn\": \"1\",\r\n" + 
        //                "        \"msgeTitlNm\": \"모바일 화상상담 서비스 사용 안내\",\r\n" + 
                "        \"sndeTlno\": \"\",\r\n" + 
                "        \"sbsnSendYn\": \"Y\",\r\n" + 
                "        \"onlnBtchDvsnCode\": \"R\",\r\n" + 
                "        \"ntfcMdiaDvsnCode\": \"LMS\",\r\n" + 
                "        \"custId\": \"5019808635\",\r\n" + 
                "        \"tmplCode\": \"\",\r\n" + 
                "        \"rcvrNm\": \"신용진\",\r\n" + 
                "        \"sbsnSendMsgeCntn\": \"[한화생명] 모바일 화상상담 서비스 사용 안내\\r\\n\\r\\n신용진 고객님 안녕하세요. \\r\\n모바일 화상상담 서비스를 신청해 주셔서 감사합니다. \\r\\n하단의 '바로가기' 버튼을 클릭 해 주시면 모바일 화상상담 서비스가 시작됩니다. \\r\\n\\r\\n주소링크 : https://www.hanwhalife.com \\r\\n\\r\\n서비스 연결은 메세지를 받으신 후 30분 간 유효함을 안내 드립니다. \\r\\n감사합니다.\\r\\n\\r\\n* 서비스 연결 시 5G/LTE 환경에서 데이터 통화료가 발생할 수 있습니다.\",\r\n" + 
                "        \"dutySendYn\": \"Y\",\r\n" + 
                "        \"butnDvsnCode\": \"2\",\r\n" + 
        //                "        \"nttkButnCntn\": \"{\\\"button\\\":[{\\\"name\\\":\\\"한화 테스트 URL\\\",\\\"type\\\":\\\"WL\\\",\\\"url_pc\\\":\\\"https://www.hanwhalife.com\\\",\\\"url_mobile\\\":\\\"https://www.hanwhalife.com\\\",\\\"target\\\":\\\"out\\\"}]}\",\r\n" + 
                "        \"sndeTlphArcd\": \"\",\r\n" + 
                "        \"sndeTlphOfno\": \"1588\",\r\n" + 
                "        \"sndeTlphInno\": \"6363\",\r\n" + 
                "        \"hpTlphTlcmNo\": \"010\",\r\n" + 
                "        \"hpTlphOfno\": \"2889\",\r\n" + 
                "        \"hpTlphSbno\": \"3661\",\r\n" + 
                "        \"ntfcKindCode\": \"ZAU0006\"\r\n" + 
                "    }";
        */
        
        
        

        
        String payloadJson = "{\r\n" + 
                "        \"sendCont\": 1,\r\n" + 
//                "        \"jobMsgeCntn\": \"[한화생명] 모바일 화상상담 서비스 사용 안내\\r\\n\\r\\n신용진 고객님 안녕하세요. \\r\\n모바일 화상상담 서비스를 신청해 주셔서 감사합니다. \\r\\n하단의 '바로가기' 버튼을 클릭 해 주시면 모바일 화상상담 서비스가 시작됩니다. \\r\\n\\r\\n서비스 연결은 메세지를 받으신 후 30분 간 유효함을 안내 드립니다. \\r\\n감사합니다.\\r\\n\\r\\n* 서비스 연결 시 5G/LTE 환경에서 데이터 통화료가 발생할 수 있습니다.\",\r\n" + 
//                "        \"jobMsgeCntn\": \"" + a + "\",\r\n" +
                "        \"trnnPrgmId\": null,\r\n" + 
                "        \"sndeDeptCode\": \"00630\",\r\n" + 
                "        \"sendRsvtDttm\": null,\r\n" + 
                "        \"ntfcTmplCode\": \"AZAU000001111\",\r\n" + 
                "        \"rcvrTlno\": \"\",\r\n" + 
                "        \"btchPrcsYn\": \"1\",\r\n" + 
//                "        \"msgeTitlNm\": \"모바일 화상상담 서비스 사용 안내\",\r\n" + 
                "        \"sndeTlno\": \"\",\r\n" + 
                "        \"sbsnSendYn\": \"Y\",\r\n" + 
                "        \"onlnBtchDvsnCode\": \"R\",\r\n" + 
                "        \"ntfcMdiaDvsnCode\": \"LMS\",\r\n" + 
                "        \"custId\": \"5019808635\",\r\n" + 
                "        \"tmplCode\": \"\",\r\n" + 
                "        \"rcvrNm\": \"신용진\",\r\n" + 
                "        \"sbsnSendMsgeCntn\": \"[한화생명] 모바일 화상상담 서비스 사용 안내\\r\\n\\r\\n신용진 고객님 안녕하세요. \\r\\n모바일 화상상담 서비스를 신청해 주셔서 감사합니다. \\r\\n하단의 '바로가기' 버튼을 클릭 해 주시면 모바일 화상상담 서비스가 시작됩니다. \\r\\n\\r\\n주소링크 : https://www.hanwhalife.com \\r\\n\\r\\n서비스 연결은 메세지를 받으신 후 30분 간 유효함을 안내 드립니다. \\r\\n감사합니다.\\r\\n\\r\\n* 서비스 연결 시 5G/LTE 환경에서 데이터 통화료가 발생할 수 있습니다.\",\r\n" + 
                "        \"dutySendYn\": \"Y\",\r\n" + 
                "        \"butnDvsnCode\": \"2\",\r\n" + 
//                "        \"nttkButnCntn\": \"{\\\"button\\\":[{\\\"name\\\":\\\"한화 테스트 URL\\\",\\\"type\\\":\\\"WL\\\",\\\"url_pc\\\":\\\"https://www.hanwhalife.com\\\",\\\"url_mobile\\\":\\\"https://www.hanwhalife.com\\\",\\\"target\\\":\\\"out\\\"}]}\",\r\n" + 
                "        \"sndeTlphArcd\": \"\",\r\n" + 
                "        \"sndeTlphOfno\": \"1588\",\r\n" + 
                "        \"sndeTlphInno\": \"6363\",\r\n" + 
                "        \"hpTlphTlcmNo\": \"010\",\r\n" + 
                "        \"hpTlphOfno\": \"2889\",\r\n" + 
                "        \"hpTlphSbno\": \"3661\",\r\n" + 
                "        \"ntfcKindCode\": \"ZAU0006\"\r\n" + 
                "    }";
        
        
        
        
//        IfMcCs015_I2 inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs015_I2.class);
        // XXX
        IfMcCs015_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs015_I.class);
//        String hpTlphSbno = inputPayload.getHpTlphSbno();
        
        inputPayload.setJobMsgeCntn(jobMsgeCntn);
        inputPayload.setMsgeTitlNm(msgeTitlNm);
        
        // XXX
//        IfMcCs015_I2.NttkButnCntn.Button button = new IfMcCs015_I2.NttkButnCntn.Button();
        IfMcCs015_I.NttkButnCntn.Button button = new IfMcCs015_I.NttkButnCntn.Button();
        button.setName("모바일 화상상담 바로가기");
        button.setType("WL");
        button.setUrl_pc("");
        button.setUrl_mobile("https://www.hanwhalife.com");
        button.setTarget("out");
        
//        List<Button> buttonList = new ArrayList<>();
        // XXX
        List<com.gooroomee.backbone.external.dto.intrf.IfMcCs015_I.NttkButnCntn.Button> buttonList = new ArrayList<>();
        buttonList.add(button);
        
//        IfMcCs015_I2.NttkButnCntn nttkButnCntn = new IfMcCs015_I2.NttkButnCntn();
        // XXX
        IfMcCs015_I.NttkButnCntn nttkButnCntn = new IfMcCs015_I.NttkButnCntn();
        nttkButnCntn.setButton(buttonList);
        
        inputPayload.setNttkButnCntn(nttkButnCntn);
        
//        String nttkButnCntn = "{ \"button\": [ { \"name\": \"한화 테스트 URL\", \"type\": \"WL\", \"url_pc\": \"https://www.hanwhalife.com\", \"url_mobile\": \"https://www.hanwhalife.com\", \"target\": \"out\" } ] }";
//        inputPayload.setNttkButnCntn(nttkButnCntn);
        
        
        
        
        
        
        
        
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
//        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs015_2;
        // XXX
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs015;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
//        IfTelegram<IfMcCs015_O2> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload, IfMcCs015_O2.class);
        // XXX
        IfTelegram<IfMcCs015_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload, IfMcCs015_O.class);
    
//        IfMcCs015_O2 outputPayload = outputTelegram.getPayload();
        // XXX
        IfMcCs015_O outputPayload = outputTelegram.getPayload();
    
//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }
    
    
    
    /**
     * 대체키별연락처저장
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf016() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
    
        String payloadJson = "{\r\n" + 
                "    \"elctLoct\" : { },\r\n" + 
                "    \"sns\" : { },\r\n" + 
                "    \"addr\" : { },\r\n" + 
                "    \"tlno\" : {\r\n" + 
                "      \"tlphIdnfCode\" : \"010\",\r\n" + 
                "      \"tlphSbno\" : \"5646\",\r\n" + 
                "      \"tlphOfno\" : \"5209\"\r\n" + 
                "    }\r\n" + 
                "  }";
    
        IfMcCs016_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs016_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs016;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs016_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload, IfMcCs016_O.class);
    
        IfMcCs016_O outputPayload = outputTelegram.getPayload();
    
//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }
    
    
    /**
     * 대체키별연락처조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf017() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        /*
        String payloadJson = "{\r\n" + 
                "    \"sbsnKeySuid\" : 435468352206471303\r\n" + 
                "  }";
        */
        
        String payloadJson = "{\r\n" + 
                "    \"sbsnKeySuid\" : 435468352206471304\r\n" + 
                "  }";
        
        IfMcCs017_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs017_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs017;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs017_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload, IfMcCs017_O.class);
    
        IfMcCs017_O outputPayload = outputTelegram.getPayload();
    
//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }
    
    
    
    /**
     * 우편번호조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf018() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        /*
        String payloadJson = "{\r\n" + 
                "    \"scwdNm\" : \"가로공원로 82길\"\r\n" + 
                "  }";
        */
        
        String payloadJson = "{\r\n" + 
                "    \"scwdNm\" : \"가로공원로 1182길\"\r\n" + 
                "  }";
        
        IfMcCs018_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs018_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs018;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs018_O> outputTelegram = ifUtil.exchangeTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload, IfMcCs018_O.class);
    
        IfMcCs018_O outputPayload = outputTelegram.getPayload();
    
//        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
        logger.debug("[" + thisMethodName + "]" + "[outputTelegram] : " + OBJECT_MAPPER.writeValueAsString(outputTelegram));
    }
    
    
    
    
    
    
    
    
    
    @Getter
    @Setter
    @ToString
    public static class TestDto {
        private String CRTF_RTCD;
        private String DLRE_MSG;
        private String SCRN_ID;
        private String X_OCR_SECRET;
        private String SRVC_ID;
    }
    
    public void doTest_00001() {
        logger.debug("doTest");
    }
    
    
    public void doTest_00002() throws JsonMappingException, JsonProcessingException {
        
        String json = "{\r\n" + 
                "            \"crtf_RTCD\": \"\",\r\n" + 
                "            \"dlre_MSG\": \"\",\r\n" + 
                "            \"scrn_ID\": \"\",\r\n" + 
                "            \"x_OCR_SECRET\": \"ckZJZ29HcG1OZFF5WXJ3TXBYSXZlbUJzWmhGbmx6Ylc=\",\r\n" + 
                "            \"srvc_ID\": \"SVC028\"\r\n" + 
                "        }";
        
        TestDto readValue = OBJECT_MAPPER.readValue(json, TestDto.class);
        
        System.out.println(readValue);
        
    }
    
    public void doTest_encryptDecrypt() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        final String AES_KEY = "TzK5/8gFpMXmTKH5aYS6Uw9j2UwBGwGeju46fsJDwNE=";
        final String AES_IV = "vxg7xjhMOVsLDmPU+Wfp5g==";

        String encrypt = AesUtil.encrypt("asdf", AES_KEY, AES_IV);
        System.out.println("encrypt : " + encrypt);
        String decrypt = AesUtil.decrypt(encrypt, AES_KEY, AES_IV);
        System.out.println("decrypt : " + decrypt);
    }
    
    public void doTest_jacksonPascalCase() throws JsonMappingException, JsonProcessingException {
        String json = "{\r\n" + 
                "    \"header\": {\r\n" + 
                "        \"trnmSysCode\": \"MVC\",\r\n" + 
                "        \"ipAddr\": \"010252005065\",\r\n" + 
                "        \"tlgrCretDttm\": \"20231023085434873\",\r\n" + 
                "        \"rndmNo\": \"9791\",\r\n" + 
                "        \"hsno\": 0,\r\n" + 
                "        \"ctfnTokn\": \"\",\r\n" + 
                "        \"ogtsTrnnNo\": \"\",\r\n" + 
                "        \"prsnInfoIncsYn\": \"N\",\r\n" + 
                "        \"itfcId\": \"HLIMVC00001\",\r\n" + 
                "        \"rcveSrvcId\": \"hcsIdcdOcrRqst\",\r\n" + 
                "        \"rcveSysCode\": \"HCS\",\r\n" + 
                "        \"mciNodeNo\": \"\",\r\n" + 
                "        \"mciSesnId\": \"\",\r\n" + 
                "        \"serverType\": \"Q\",\r\n" + 
                "        \"rspnDvsnCode\": \"S\",\r\n" + 
                "        \"extlDvsnCode\": \"\",\r\n" + 
                "        \"emnb\": \"1077593\",\r\n" + 
                "        \"belnOrgnCode\": \"00630\",\r\n" + 
                "        \"custId\": \"\",\r\n" + 
                "        \"chnlTypeCode\": \"SVR\",\r\n" + 
                "        \"scrnId\": \"\",\r\n" + 
                "        \"befoScrnId\": \"\",\r\n" + 
                "        \"userTmunIdnfVal\": \"\",\r\n" + 
                "        \"rqsrIp\": \"\",\r\n" + 
                "        \"rqstDttm\": \"\",\r\n" + 
                "        \"baseCrny\": \"\",\r\n" + 
                "        \"baseCnty\": \"\",\r\n" + 
                "        \"baseLang\": \"\",\r\n" + 
                "        \"tlgrRspnDttm\": \"\",\r\n" + 
                "        \"prcsRsltDvsnCode\": \"\",\r\n" + 
                "        \"totalCount\": 0,\r\n" + 
                "        \"lastPageYn\": \"N\",\r\n" + 
                "        \"msgeListCont\": 0,\r\n" + 
                "        \"msgeList\": [],\r\n" + 
                "        \"msgeStackTrace\": \"\"\r\n" + 
                "    },\r\n" + 
                "    \"payload\": {\r\n" + 
                "        \"dataHeader\": {\r\n" + 
                "            \"srvc_ID\": \"SVC028\",\r\n" + 
                "            \"dlre_MSG\": \"\",\r\n" + 
                "            \"x_OCR_SECRET\": \"ckZJZ29HcG1OZFF5WXJ3TXBYSXZlbUJzWmhGbmx6Ylc=\",\r\n" + 
                "            \"crtf_RTCD\": \"\",\r\n" + 
                "            \"scrn_ID\": \"\"\r\n" + 
                "        },\r\n" + 
                "        \"dataBody\": {\r\n" + 
                "            \"images\": [\r\n" + 
                "                {\r\n" + 
                "                    \"format\": \"png\",\r\n" + 
                "                    \"data\": \"\",\r\n" + 
                "                    \"name\": \"test_idcard\"\r\n" + 
                "                }\r\n" + 
                "            ],\r\n" + 
                "            \"user_ID\": \"\",\r\n" + 
                "            \"orgn_CODE\": \"00630\"\r\n" + 
                "        }\r\n" + 
                "    }\r\n" + 
                "}";
        
            
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(IfTelegram.class,
                IfMcCs001_I.class);
        Object readValue = OBJECT_MAPPER.readValue(json, javaType);
        System.out.println("*****");
        System.out.println(OBJECT_MAPPER.writeValueAsString(readValue));
        System.out.println("*****");
    }
}
