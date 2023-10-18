package test;

import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gooroomee.gooroomeeadapter.constant.IfConstant;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.IfSpec;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs005_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs005_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs006_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs006_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegram;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegramHeader;
import com.gooroomee.gooroomeeadapter.util.IfAdapter;

public class IfTest {

    private static final Logger logger = LoggerFactory.getLogger(IfTest.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static final String EMNB = "1077593";
    //    public static final String EMNB = "1073818";

    public static final String ACTIVE_PROFILE = "qa";

    public static final String IF_ENDPOINT_URL = "https://qainf.hanwhalife.com:8713";

    public static void main(String[] args) throws JsonProcessingException, URISyntaxException {
        IfTest ifTest = new IfTest();

        ifTest.doTest();
        
//        ifTest.testIf002();
        
//        ifTest.testIf003();
        
//        ifTest.testIf005();
        
//        ifTest.testIf006();
    }

    
    /**
     * 진위확인결과조회
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public void testIf002() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        
        String payloadJson = "{\r\n" + 
                "    \"trflCnfmDvsnCode\" : \"011\",\r\n" + 
                "    \"trflCnfmBswrDvsnCode\" : \"01\",\r\n" + 
                "    \"trflCnfmChnlCode\" : \"01\",\r\n" + 
                "    \"prcsBswrScrnId\" : \"MSESTEP05\",\r\n" + 
                "    \"trflCnfmJobCode\" : \"CS001\",\r\n" + 
                "    \"custId\" : \"0052778800\",\r\n" + 
                "    \"custNm\" : \"최주열\",\r\n" + 
                "    \"isncDate\" : \"\",\r\n" + 
                "    \"btdt\" : \"19881022\",\r\n" + 
                "    \"drvnLcnsSqno\" : \"\",\r\n" + 
                "    \"rrno\" : \"8810221229923\",\r\n" + 
                "    \"drvnLcnsNo\" : \"130760650530\",\r\n" + 
                "    \"frnrRgstNo\" : \"\",\r\n" + 
                "    \"psprNo\" : \"\",\r\n" + 
                "    \"expyDate\" : \"\"\r\n" + 
                "  }";
        
        
        IfMcCs002_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs002_I.class);

        IfAdapter ifAdapter = new IfAdapter(EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs002;

        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs002_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload, IfMcCs002_O.class);

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
        
        String payloadJson = "{\r\n" + 
                "    \"csnsYn\": \"Y\",\r\n" + 
                "    \"pushRcvrEmnb\": \"2030205\",\r\n" + 
                "    \"custId\": \"1005132571\"\r\n" + 
                "}";

        
        IfMcCs003_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs003_I.class);
        
        inputPayload.setPushRcvrEmnb(EMNB); // 사번 overwrite

        IfAdapter ifAdapter = new IfAdapter(EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs003;

        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs003_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload, IfMcCs003_O.class);

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
        
        String payloadJson = "{\r\n" + 
                "    \"emnb\" : \"1077739\",\r\n" + 
                "    \"lognPswd\" : \"EixO7RZy6bW7Wa.qxs1qs6mfF4e81tGf\"\r\n" + 
                "  }";

        
        IfMcCs005_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs005_I.class);
        
        IfAdapter ifAdapter = new IfAdapter(EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs005;

        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs005_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload, IfMcCs005_O.class);

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
        
        String payloadJson = "{\r\n" + 
                "    \"scwdNm\" : \"1871261\",\r\n" + 
                "    \"orgnCode\" : \"\",\r\n" + 
                "    \"empeDvsnCode\" : \"\",\r\n" + 
                "    \"wholEmpeInqyYn\" : \"N\",\r\n" + 
                "    \"fpExlsYn\" : \"N\",\r\n" + 
                "    \"tnofDvsnCode\" : \"\"\r\n" + 
                "  }";

        
        IfMcCs006_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs006_I.class);
        
        IfAdapter ifAdapter = new IfAdapter(EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs006;

        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs006_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload, IfMcCs006_O.class);

        IfMcCs006_O outputPayload = outputTelegram.getPayload();

        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + objectMapper.writeValueAsString(outputPayload));
    }
    
    
    public void doTest() {
        logger.debug("doTest");
    }
}
