package _test;

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

//        ifTest.testIf002();
        
        ifTest.testIf003();
        
//        ifTest.testIf005();
    }

    public void testIf002() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        
        String payloadJson = "{\r\n" + 
                "    \"drvnLcnsSqno\": \"E8VT33\",\r\n" + 
                "    \"trflCnfmJobCode\": \"CS001\",\r\n" + 
                "    \"custNm\": \"조상희\",\r\n" + 
                "    \"rrno\": \"*************\",\r\n" + 
                "    \"frnrRgstNo\": \"\",\r\n" + 
                "    \"trflCnfmDvsnCode\": \"011\",\r\n" + 
                "    \"trflCnfmBswrDvsnCode\": \"99\",\r\n" + 
                "    \"drvnLcnsNo\": \"************\",\r\n" + 
                "    \"trflCnfmChnlCode\": \"01\",\r\n" + 
                "    \"btdt\": \"19820210\",\r\n" + 
                "    \"isncDate\": \"20190916\",\r\n" + 
                "    \"prcsBswrScrnId\": \"HLImgScanApp_ID\"\r\n" + 
                "}";
        
        
        IfMcCs002_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs002_I.class);

        IfAdapter ifAdapter = new IfAdapter(EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs002;

        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs002_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload, IfMcCs002_O.class);

        IfMcCs002_O outputPayload = outputTelegram.getPayload();

        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + objectMapper.writeValueAsString(outputPayload));
    }

    
    
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
    
    
    public void testIf005() throws JsonProcessingException, URISyntaxException {
        String thisMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        
        String payloadJson = "{\r\n" + 
                "    \"emnb\": \"1961255\",\r\n" + 
                "    \"lognPswd\": \"eS8ZSKIQ\\/aXpDOidiCfGeAKy8ieGeRq7\"\r\n" + 
                "}";

        
        IfMcCs005_I inputPayload = objectMapper.readValue(payloadJson, IfMcCs005_I.class);
        inputPayload.setEmnb("1077593");
        inputPayload.setLognPswd("hlihli1!");
        
        IfAdapter ifAdapter = new IfAdapter(EMNB, ACTIVE_PROFILE, IF_ENDPOINT_URL);

        IfSpec ifSpec = IfConstant.IfSpec.IfMcCs005;

        IfTelegramHeader inputHeader = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode());

        IfTelegram<IfMcCs005_O> outputTelegram = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.ESB, inputHeader, inputPayload, IfMcCs005_O.class);

        IfMcCs005_O outputPayload = outputTelegram.getPayload();

        logger.debug("[" + thisMethodName + "]" + "[outputPayload] : " + objectMapper.writeValueAsString(outputPayload));
    }
}
