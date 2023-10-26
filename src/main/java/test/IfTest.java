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
import com.gooroomee.gooroomeeadapter.constant.IfConstant;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.IfSpec;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs001_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs001_I.DataBody;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs001_I.DataBody.Image;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs001_I.DataHeader;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs001_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs005_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs005_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs006_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs006_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs007_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs007_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs008_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs008_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs009_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs009_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs010_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs010_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs012_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs012_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs015_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs015_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs016_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs016_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs017_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs017_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs018_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs018_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegram;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegramHeader;
import com.gooroomee.gooroomeeadapter.util.AesUtil;
import com.gooroomee.gooroomeeadapter.util.IfUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class IfTest {

    private static final Logger logger = LoggerFactory.getLogger(IfTest.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
    													.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    													.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
    													.registerModule(new SimpleModule())
    													;
    
    
    public static final RestTemplate REST_TEMPLATE = new RestTemplate();
    

    public static final String EMNB = "1077593";
    //    public static final String EMNB = "1073818";

    /*
    public static final String ACTIVE_PROFILE = "qa";
    public static final String IF_ENDPOINT_URL = "https://qainf.hanwhalife.com:8713";
    */
    
    public static final String ACTIVE_PROFILE = "dev";
    public static final String IF_ENDPOINT_URL = "http://devinf.hanwhalife.com:7711";
    

    public static final String ORGN_CODE = "00630";
    
    public static final String OCR_URL = "https://kyxtjoiqax.apigw-pub.fin-ntruss.com/custom/v1/5129/c6aba620e83d8ca37e578ac6f8c5211267b5b93279ea2fe205a0708ef0f5ac4c/document/id-card ";

    public static final String X_OCR_SECRET = "ckZJZ29HcG1OZFF5WXJ3TXBYSXZlbUJzWmhGbmx6Ylc=";
    
    public static final String SCRN_ID = "";
    
    public static final String SRVC_ID = "SVC028";
    
    

    public static void main(String[] args) throws URISyntaxException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
                IfTest ifTest = new IfTest();
//                ifTest.doTest_00001();
//                ifTest.doTest_00002();
//                ifTest.doTest_encryptDecrypt();
        
//                ifTest.doTest_jacksonPascalCase();
        
                // XXX 성공
//                ifTest.testIf001();
                
        
                // XXX 성공
//                ifTest.testIf002();

        
                // XXX 성공
//                ifTest.testIf003();
        
        
                // XXX 실패 - 비밀번호오류
//                ifTest.testIf005();


                // XXX 성공
//                ifTest.testIf006();

                // XXX 성공
//                ifTest.testIf007();

                // XXX 성공        
//                ifTest.testIf008();

                // XXX 성공
//                ifTest.testIf009();

                // XXX 성공
//                ifTest.testIf010();
        
//                ifTest.testIf011();
//                ifTest.testIf012();
        
                // XXX 실패
//                ifTest.testIf015();
        
                // XXX 성공
//                ifTest.testIf016();
        
                // XXX 성공
//                ifTest.testIf017();
                
                // XXX 성공
//                ifTest.testIf018();
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
        
        
        IfTelegram<IfMcCs001_O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload, IfMcCs001_O.class);
        
        IfMcCs001_O outputPayload = outputTelegram.getPayload();
        
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
       
        
    }
    
    
    
    /**
     * 진위확인결과조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf002() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();

        String payloadJson = "{\r\n" + "    \"trflCnfmDvsnCode\" : \"011\",\r\n" + "    \"trflCnfmBswrDvsnCode\" : \"01\",\r\n"
                + "    \"trflCnfmChnlCode\" : \"01\",\r\n" + "    \"prcsBswrScrnId\" : \"MSESTEP05\",\r\n"
                + "    \"trflCnfmJobCode\" : \"CS001\",\r\n" + "    \"custId\" : \"0052778800\",\r\n"
                + "    \"custNm\" : \"최주열\",\r\n" + "    \"isncDate\" : \"\",\r\n" + "    \"btdt\" : \"19881022\",\r\n"
                + "    \"drvnLcnsSqno\" : \"\",\r\n" + "    \"rrno\" : \"8810221229923\",\r\n"
                + "    \"drvnLcnsNo\" : \"130760650530\",\r\n" + "    \"frnrRgstNo\" : \"\",\r\n" + "    \"psprNo\" : \"\",\r\n"
                + "    \"expyDate\" : \"\"\r\n" + "  }";

        IfMcCs002_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs002_I.class);

        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs002;

        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs002_O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs002_O.class);

        IfMcCs002_O outputPayload = outputTelegram.getPayload();

        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
    }

    /**
     * 신분증스캔후처리  
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf003() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();

        String payloadJson = "{\r\n" + "    \"csnsYn\": \"Y\",\r\n" + "    \"pushRcvrEmnb\": \"2030205\",\r\n"
                + "    \"custId\": \"1005132571\"\r\n" + "}";

        IfMcCs003_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs003_I.class);

        inputPayload.setPushRcvrEmnb(EMNB); // 사번 overwrite

        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs003;

        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs003_O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs003_O.class);

        IfMcCs003_O outputPayload = outputTelegram.getPayload();

        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
    }

    /**
     * SSO대체로그인인증 
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf005() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        
        /*
        String payloadJson = "{\r\n" + "    \"emnb\" : \"1077739\",\r\n"
                + "    \"lognPswd\" : \"EixO7RZy6bW7Wa.qxs1qs6mfF4e81tGf\"\r\n" 
                + "  }";
        */
        
        String payloadJson = "{\r\n" + 
                "    \"emnb\" : \"1021814\",\r\n" + 
                "    \"lognPswd\" : \"8cFbvEcq.7DsTqDQsTbMCxYPvC15bsmn\"\r\n" + 
                "  }";
        
        
        IfMcCs005_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs005_I.class);

        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs005;

        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs005_O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs005_O.class);

        IfMcCs005_O outputPayload = outputTelegram.getPayload();

        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
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

        IfMcCs006_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs006_I.class);

        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs006;

        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs006_O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs006_O.class);

        IfMcCs006_O outputPayload = outputTelegram.getPayload();

        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
    }

    /**
     * 고객계약정보조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    
    public void testIf007() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        
        String payloadJson = "{\r\n" + 
                "    \"custId\" : \"8061129138\",\r\n" + 
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
    
        IfTelegram<IfMcCs007_O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs007_O.class);
    
        IfMcCs007_O outputPayload = outputTelegram.getPayload();
    
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
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
        
        String payloadJson = "{\r\n" + 
                "    \"custId\": \"6068807976\"\r\n" + 
                "}";
        
        IfMcCs008_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs008_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs008;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs008_O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs008_O.class);
    
        IfMcCs008_O outputPayload = outputTelegram.getPayload();
    
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
    }
    
    
    /**
     * 개인정보유출노출여부조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf009() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();

        String payloadJson = "{\r\n" + 
                "    \"custId\": \"3002220133\"\r\n" + 
                "}";

        IfMcCs009_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs009_I.class);

        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs009;

        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs009_O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs009_O.class);

        IfMcCs009_O outputPayload = outputTelegram.getPayload();

        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
    }

    
    
    /**
     * 간편인증 토큰발급
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf010() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
    
        String payloadJson = "{\r\n" + 
                "    \"dataHeader\": {\r\n" + 
                "        \"SRVC_ID\": \"SVC028\",\r\n" + 
                "        \"SCRN_ID\": \"화면 ID\",\r\n" + 
                "        \"CRTF_RTCD\": \"\",\r\n" + 
                "        \"ORGN_CODE\": \"00630\",\r\n" + 
                "        \"USER_ID\": \"USER_001\"\r\n" + 
                "    },\r\n" + 
                "    \"dataBody\": {\r\n" + 
                "        \"grant_type\": \"client_credentials\"\r\n" + 
                "    }\r\n" + 
                "}";
    
        IfMcCs010_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs010_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs010;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs010_O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs010_O.class);
    
        IfMcCs010_O outputPayload = outputTelegram.getPayload();
    
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
    }
    
    
    
    
    
    /**
     * 간편인증 요청
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    /*
    public void testIf011() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
    
        String payloadJson = "";
        
        
        com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs011_I.DataHeader dataHeader = new IfMcCs011_I.DataHeader();
        dataHeader.setSRVC_ID(SRVC_ID);
        dataHeader.setSCRN_ID("화면 ID");
        dataHeader.setORGN_CODE(ORGN_CODE);
        dataHeader.setUSER_ID(USER_ID);
        
    
        @JsonProperty("USER_ID")
        private String USER_ID;
        
        
        String userId = "USER001";
        dataHeader.setUSER_ID(userId);
    
        
        com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs011_I.DataBody dataBody = new IfMcCs011_I.DataBody();
        
        String initechOAuthToken 
        dataBody.setInitechOAuthToken();
        
        Callback callback = new IfMcCs011_I.DataBody.Callback();
        Sign sign = new IfMcCs011_I.DataBody.Sign();
        
        
    
        IfMcCs011_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs011_I.class);
    
        IfAdapter ifAdapter = new IfAdapter(EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs011;
    
        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs011_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs011_O.class);
    
        IfMcCs011_O outputPayload = outputTelegram.getPayload();
    
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
    }
    */
    
    
    
    /**
     * 간편인증 상태 조회 
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf012() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
    
        String payloadJson = "";
    
        IfMcCs012_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs012_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs012;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs012_O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs012_O.class);
    
        IfMcCs012_O outputPayload = outputTelegram.getPayload();
    
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
    }
    
    
    /**
     * 알림톡전송
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    
    public void testIf015() throws JsonProcessingException, URISyntaxException {
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
                "    \"aentEmnb\": \"1077593\",\r\n" + 
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
        
    
        IfMcCs015_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs015_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs015;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs015_O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload,
                IfMcCs015_O.class);
    
        IfMcCs015_O outputPayload = outputTelegram.getPayload();
    
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
    }
    
    
    
    /**
     * 대체키별연락처저장
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf016() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
    
        String payloadJson = "{\r\n" + 
                "    \"sns\" : { },\r\n" + 
                "    \"addr\" : { },\r\n" + 
                "    \"elctLoct\" : { },\r\n" + 
                "    \"tlno\" : {\r\n" + 
                "      \"cntyCode\" : \"\",\r\n" + 
                "      \"tlphIdnfCode\" : \"\",\r\n" + 
                "      \"tlphOfno\" : \"1588\",\r\n" + 
                "      \"tlphSbno\" : \"6363\"\r\n" + 
                "    }\r\n" + 
                "  }";
    
        IfMcCs016_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs016_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs016;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs016_O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload, IfMcCs016_O.class);
    
        IfMcCs016_O outputPayload = outputTelegram.getPayload();
    
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
    }
    
    
    /**
     * 대체키별연락처조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf017() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
    
        String payloadJson = "{\r\n" + 
                "    \"sbsnKeySuid\" : 171766277056300198\r\n" + 
                "  }";
    
        IfMcCs017_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs017_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs017;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs017_O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload, IfMcCs017_O.class);
    
        IfMcCs017_O outputPayload = outputTelegram.getPayload();
    
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
    }
    
    
    
    /**
     * 우편번호조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf018() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
    
        String payloadJson = "{\r\n" + 
                "    \"scwdNm\" : \"가로공원로 82길\"\r\n" + 
                "  }";
    
        IfMcCs018_I inputPayload = OBJECT_MAPPER.readValue(payloadJson, IfMcCs018_I.class);
    
        IfUtil ifUtil = new IfUtil(REST_TEMPLATE, EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs018;
    
        IfTelegramHeader inputHeader = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs018_O> outputTelegram = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, inputHeader, inputPayload, IfMcCs018_O.class);
    
        IfMcCs018_O outputPayload = outputTelegram.getPayload();
    
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + OBJECT_MAPPER.writeValueAsString(outputPayload));
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
