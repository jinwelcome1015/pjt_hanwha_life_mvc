package test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs009_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs009_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs010_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs015_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs015_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs016_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs016_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs017_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs017_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegram;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegramHeader;
import com.gooroomee.gooroomeeadapter.util.IfAdapter;

public class IfTest {

    private static final Logger logger = LoggerFactory.getLogger(IfTest.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    

    public static final String EMNB = "1077593";
    //    public static final String EMNB = "1073818";

    public static final String ACTIVE_PROFILE = "qa";

    public static final String ORGN_CODE = "00630";
    
    public static final String OCR_URL = "https://kyxtjoiqax.apigw-pub.fin-ntruss.com/custom/v1/5129/c6aba620e83d8ca37e578ac6f8c5211267b5b93279ea2fe205a0708ef0f5ac4c/document/id-card ";

    public static final String X_OCR_SECRET = "ckZJZ29HcG1OZFF5WXJ3TXBYSXZlbUJzWmhGbmx6Ylc=";
    
    public static final String SCRN_ID = "";
    
    public static final String SRVC_ID = "SVC028";
    
    public static final String IF_ENDPOINT_URL = "https://qainf.hanwhalife.com:8713";
    

    public static void main(String[] args) throws URISyntaxException, IOException {
        IfTest ifTest = new IfTest();
                  
//                ifTest.doTest();          // 실패
        
                // XXX 실패
//                ifTest.testIf001();       

//                ifTest.testIf002();

//                ifTest.testIf003();
        
                // XXX 비밀번호오류
//                ifTest.testIf005();

//                ifTest.testIf006();ㅓ

                // XXX 실패
//                ifTest.testIf007();ㅓ
        
//                  ifTest.testIf009();
        
                // XXX 재시도
//                ifTest.testIf015();
        
//        ifTest.testIf016();
        ifTest.testIf017();
    }

    private RestTemplate getRestTemplate() {
		return null;
	}
    
    
    /**
     * 진위확인결과조회
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
//        IfMcCs001_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs001_I.class);
        
        
        DataHeader dataHeader = new IfMcCs001_I.DataHeader();
        dataHeader.setCRTF_RTCD("");
        dataHeader.setDLRE_MSG("");
        dataHeader.setSCRN_ID(SCRN_ID);
        dataHeader.setSRVC_ID(SRVC_ID);
        dataHeader.setX_OCR_SECRET(X_OCR_SECRET);
        
        
        String ocrFilePath = "C:\\_\\ocr\\sample_idcard.dat";
        
        File file = new File(ocrFilePath);
        Path path = file.toPath();
        
        List<String> lines = Files.readAllLines(path);
        
        String delimiter = "";
        String ocrData = String.join(delimiter, lines);
        
        String refinedOcrData = ocrData.replaceAll("^data:image/png;base64,", "");
        
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
        
        
        
        
        IfAdapter ifAdapter = new IfAdapter(getRestTemplate(), EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs001;

        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode());
        
        
        IfTelegram<IfMcCs001_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload, IfMcCs001_O.class);
        
        IfMcCs001_O outputPayload = outputTelegram.getPayload();
        
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + objectMapper.writeValueAsString(outputPayload));
       
        
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

        IfMcCs002_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs002_I.class);

        IfAdapter ifAdapter = new IfAdapter(getRestTemplate(), EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs002;

        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs002_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload,
                IfMcCs002_O.class);

        IfMcCs002_O outputPayload = outputTelegram.getPayload();

        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + objectMapper.writeValueAsString(outputPayload));
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

        IfMcCs003_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs003_I.class);

        inputPayload.setPushRcvrEmnb(EMNB); // 사번 overwrite

        IfAdapter ifAdapter = new IfAdapter(getRestTemplate(), EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs003;

        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs003_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload,
                IfMcCs003_O.class);

        IfMcCs003_O outputPayload = outputTelegram.getPayload();

        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + objectMapper.writeValueAsString(outputPayload));
    }

    /**
     * SSO대체로그인인증 
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf005() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();

        String payloadJson = "{\r\n" + "    \"emnb\" : \"1077739\",\r\n"
                + "    \"lognPswd\" : \"EixO7RZy6bW7Wa.qxs1qs6mfF4e81tGf\"\r\n" + "  }";

        IfMcCs005_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs005_I.class);

        IfAdapter ifAdapter = new IfAdapter(getRestTemplate(), EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs005;

        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs005_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload,
                IfMcCs005_O.class);

        IfMcCs005_O outputPayload = outputTelegram.getPayload();

        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + objectMapper.writeValueAsString(outputPayload));
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

        IfMcCs006_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs006_I.class);

        IfAdapter ifAdapter = new IfAdapter(getRestTemplate(), EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs006;

        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs006_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload,
                IfMcCs006_O.class);

        IfMcCs006_O outputPayload = outputTelegram.getPayload();

        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + objectMapper.writeValueAsString(outputPayload));
    }

    /**
     * 고객계약정보조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    /*
    public void testIf007() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
    
        String payloadJson = "{\r\n" + "    \"custId\" : \"3002220133\",\r\n" + "    \"polyNo\" : \"\",\r\n"
                + "    \"cntcDvsnCode\" : \"\",\r\n" + "    \"custDvsnCode\" : \"\",\r\n" + "    \"nextKey\" : \"1\",\r\n"
                + "    \"pageSize\" : 10\r\n" + "  }";
    
        IfMcCs007_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs007_I.class);
    
        IfAdapter ifAdapter = new IfAdapter(EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs007;
    
        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs007_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload,
                IfMcCs007_O.class);
    
        IfMcCs007_O outputPayload = outputTelegram.getPayload();
    
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + objectMapper.writeValueAsString(outputPayload));
    }
    */
    
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

        IfMcCs009_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs009_I.class);

        IfAdapter ifAdapter = new IfAdapter(getRestTemplate(), EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs009;

        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs009_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload,
                IfMcCs009_O.class);

        IfMcCs009_O outputPayload = outputTelegram.getPayload();

        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + objectMapper.writeValueAsString(outputPayload));
    }

    
    
    /**
     * 간편인증 토큰발급
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    /*
    public void testIf010() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
    
        String payloadJson = "{\r\n" + 
                "    \"custId\": \"3002220133\"\r\n" + 
                "}";
    
        IfMcCs009_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs009_I.class);
    
        IfAdapter ifAdapter = new IfAdapter(getRestTemplate(), EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs010;
    
        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs010_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload,
                IfMcCs010_O.class);
    
        IfMcCs010_O outputPayload = outputTelegram.getPayload();
    
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + objectMapper.writeValueAsString(outputPayload));
    }
    */
    
    
    
    /**
     * 알림톡전송
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    /*
    public void testIf015() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
    
        String payloadJson = "{\r\n" + 
                "    \"sendOrchOrgnCode\" : \"00339\",\r\n" + 
                "    \"custNm\" : \"최기석\",\r\n" + 
                "    \"msgeInptYn\" : \"Y\",\r\n" + 
                "    \"ntfcKindCode\" : \"CLM0303\",\r\n" + 
                "    \"rpntMvmnTlphCnplSuid\" : 9300000000003204613,\r\n" + 
                "    \"stndCrnyDcmlPsitCnt\" : 0,\r\n" + 
                "    \"sbsnMsgeCntn\" : \"[한화생명] 보험금 신청 처리 안내\\r\r\n" + 
                "\\r\r\n" + 
                "최기석님께서 2023.10.19자 청구하신 보험금이 신청되었습니다.\\r\r\n" + 
                "접수 →서류심사 →현장심사(필요시) 단계로 처리될 예정이며 각 단계별로 별도 안내드립니다.\\r\r\n" + 
                " \\r\r\n" + 
                "▶한화생명 앱을 설치하시면 계약 조회, 보험료 납입, 보험금 청구 등 다양한 서비스를 이용하실 수 있습니다.\\r\r\n" + 
                "\\r\r\n" + 
                "항상 신속하고 정확하게 처리할 수 있도록 최선을 다하겠습니다.\r\n" + 
                "한화생명 앱 바로가기 : https://hanwhalifeplatform.page.link/bLSX\",\r\n" + 
                "    \"sndeTlnoCnplSuid\" : 171766277056300198,\r\n" + 
                "    \"mgmtNoDvsnCode\" : \"18\",\r\n" + 
                "    \"ntfcTmplCode\" : \"ACLM000026\",\r\n" + 
                "    \"rqsrEmnb\" : \"1091976\",\r\n" + 
                "    \"nttkButnCntn\" : \"{\"button\":[{\"name\":\"한화생명 앱 바로가기\",\"type\":\"AL\",\"scheme_ios\":\"https://hanwhalifeplatform.page.link/bLSX\",\"scheme_android\":\"https://hanwhalifeplatform.page.link/bLSX\",\"target\":\"out\"}]}\",\r\n" + 
                "    \"rfrnBwno\" : \"2023101974336^1^60\",\r\n" + 
                "    \"sendOrchEmnb\" : \"1091976\",\r\n" + 
                "    \"isneTypeDvsnCode\" : \"1\",\r\n" + 
                "    \"rrno\" : \"\",\r\n" + 
                "    \"msgeCntn\" : \"[한화생명] 보험금 신청 처리 안내\\r\r\n" + 
                "\\r\r\n" + 
                "최기석님께서 2023.10.19자 청구하신 보험금이 신청되었습니다.\\r\r\n" + 
                "접수 →서류심사 →현장심사(필요시) 단계로 처리될 예정이며 각 단계별로 별도 안내드립니다.\\r\r\n" + 
                " \\r\r\n" + 
                "▶한화생명 앱을 설치하시면 계약 조회, 보험료 납입, 보험금 청구 등 다양한 서비스를 이용하실 수 있습니다.\\r\r\n" + 
                "\\r\r\n" + 
                "항상 신속하고 정확하게 처리할 수 있도록 최선을 다하겠습니다.\",\r\n" + 
                "    \"msgeTitlNm\" : \"이미지 신청 알림톡\",\r\n" + 
                "    \"custId\" : \"0052611250\",\r\n" + 
                "    \"ntfcRfdt\" : \"20231019\",\r\n" + 
                "    \"actlDatYn\" : \"N\"\r\n" + 
                "  }";
    
        IfMcCs015_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs015_I.class);
    
        IfAdapter ifAdapter = new IfAdapter(getRestTemplate(), EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs015;
    
        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs015_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload,
                IfMcCs015_O.class);
    
        IfMcCs015_O outputPayload = outputTelegram.getPayload();
    
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + objectMapper.writeValueAsString(outputPayload));
    }
    */
    
    
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
    
        IfMcCs016_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs016_I.class);
    
        IfAdapter ifAdapter = new IfAdapter(getRestTemplate(), EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs016;
    
        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs016_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload, IfMcCs016_O.class);
    
        IfMcCs016_O outputPayload = outputTelegram.getPayload();
    
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + objectMapper.writeValueAsString(outputPayload));
    }
    
    
    /**
     * 대체키별연락처저장
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf017() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
    
        String payloadJson = "{\r\n" + 
                "    \"sbsnKeySuid\" : 171766277056300198\r\n" + 
                "  }";
    
        IfMcCs017_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs017_I.class);
    
        IfAdapter ifAdapter = new IfAdapter(getRestTemplate(), EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);
    
        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs017;
    
        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(),
                ifSpec.getRcveSysCode());
    
        IfTelegram<IfMcCs017_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload, IfMcCs017_O.class);
    
        IfMcCs017_O outputPayload = outputTelegram.getPayload();
    
        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + objectMapper.writeValueAsString(outputPayload));
    }
    
    
    
    
    public void doTest() {
        logger.debug("doTest");
    }
}
