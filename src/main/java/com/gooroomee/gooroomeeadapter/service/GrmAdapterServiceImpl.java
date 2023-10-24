package com.gooroomee.gooroomeeadapter.service;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import com.gooroomee.gooroomeeadapter.util.IfUtil;

@Service
public class GrmAdapterServiceImpl implements GrmAdapterService {
	
	@Autowired
	private RestTemplate restTemplate;

	/** 인터페이스 엔드포인트 URL */
	@Value(value = "${spring.profiles.active}")
	private String activeProfile;

	/** 인터페이스 엔드포인트 URL */
	@Value(value = "#{propertiesFactoryBean['interface.common.endpoint.url']}")
	private String ifEndpointUrl;

	/** 인터페이스 엔드포인트 IP */
	@Value(value = "#{propertiesFactoryBean['interface.common.endpoint.ip']}")
	private String ifEndpointIp;

	/** 인터페이스 엔드포인트 PORT */
	@Value(value = "#{propertiesFactoryBean['interface.common.endpoint.port']}")
	private String ifEndpointPort;

	/** OCR URL */
	@Value(value = "#{propertiesFactoryBean['interface.ocr.url']}")
	private String ocrUrl;

	/** OCR SECRET KEY */
	@Value(value = "#{propertiesFactoryBean['interface.ocr.secret-key']}")
	private String ocrSecretKey;

	/** 암호화 AES_KEY */
	@Value(value = "#{propertiesFactoryBean['interface.encrypt.aes-key']}")
	private String encryptAesKey;

	/** 암호화 AES_IV */
	@Value(value = "#{propertiesFactoryBean['interface.encrypt.aes-iv']}")
	private String encryptAesIv;
	
	@Override
	public IfMcCs002_O ifmccs002(String emnb, IfMcCs002_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs002;

		Class<IfMcCs002_O> ifOutputDtoClass = IfMcCs002_O.class;
		
		IfUtil ifAdapter = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs002_O> outputEnvelop = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);

        IfMcCs002_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	
	
	
	@Override
	public IfMcCs003_O ifmccs003(String emnb, IfMcCs003_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs003;

		Class<IfMcCs003_O> ifOutputDtoClass = IfMcCs003_O.class;
		
		IfUtil ifAdapter = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs003_O> outputEnvelop = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);

        IfMcCs003_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	
	
	@Override
	public IfMcCs005_O ifmccs005(String emnb, IfMcCs005_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs005;

		Class<IfMcCs005_O> ifOutputDtoClass = IfMcCs005_O.class;
		
		IfUtil ifAdapter = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs005_O> outputEnvelop = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);

        IfMcCs005_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	
	
	@Override
	public IfMcCs006_O ifmccs006(String emnb, IfMcCs006_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs006;

		Class<IfMcCs006_O> ifOutputDtoClass = IfMcCs006_O.class;
		
		IfUtil ifAdapter = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs006_O> outputEnvelop = ifAdapter.sendAndReceiveMessage(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);

        IfMcCs006_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	
	
	
	/*
	public IfMcCs012_O ifmccs012(IfMcCs012_I cs012_I) throws JsonProcessingException {
	
		com.gooroomee.adapter.dto.intrf.IfMcCs012_I.DataHeader dataHeader = new IfMcCs012_I.DataHeader();
		dataHeader.setSRVC_ID(srvcId);
		dataHeader.setSCRN_ID(SCRN_ID);
		dataHeader.setCRTF_RTCD("");
		dataHeader.setDLRE_MSG("");
		dataHeader.setORGN_CODE(orgnCode);
		dataHeader.setUSER_ID(cs012_I.getDataHeader().getUSER_ID());
	
		com.gooroomee.adapter.dto.intrf.IfMcCs012_I.DataBody dataBody = new IfMcCs012_I.DataBody();
		dataBody.setInitechOAuthToken(cs012_I.getDataBody().getInitechOAuthToken());
		dataBody.setReqTxId(cs012_I.getDataBody().getReqTxId());
		dataBody.setOp("sign");
	
		IfMcCs012_I ifMcCs012_I = new IfMcCs012_I();
		ifMcCs012_I.setDataHeader(dataHeader);
		ifMcCs012_I.setDataBody(dataBody);
		
		// XXX
		ObjectMapper objectMapper = new ObjectMapper();
		String writeValueAsString = objectMapper.writeValueAsString(ifMcCs012_I);
	
		System.out.println("*** " + writeValueAsString);
		
		String user_ID = cs012_I.getDataHeader().getUSER_ID();
	
		ChannelAdapter channelAdapter = new ChannelAdapter(user_ID, activeProfile, ifEndpointUrl);
	
		IfSpec ifSpec = IfSpec.IfMcCs012;
	
		HlicpMessageHeader createHeader = channelAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
		
		return null;
	}
	*/
}
