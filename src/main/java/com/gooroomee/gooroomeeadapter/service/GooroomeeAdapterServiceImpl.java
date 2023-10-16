package com.gooroomee.gooroomeeadapter.service;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gooroomee.gooroomeeadapter.constant.IfConstant;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.IfSpec;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegram;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegramHeader;
import com.gooroomee.gooroomeeadapter.util.IfAdapter;

@Service
public class GooroomeeAdapterServiceImpl implements GooroomeeAdapterService {

	/**
	 * 인터페이스 엔드포인트 URL
	 */
	@Value(value = "${spring.profiles.active}")
	private String activeProfile;

	/**
	 * 인터페이스 엔드포인트 URL
	 */
	@Value(value = "#{propertiesFactoryBean['interface.common.endpoint.url']}")
	private String ifEndpointUrl;

	/**
	 * 인터페이스 엔드포인트 IP
	 */
	@Value(value = "#{propertiesFactoryBean['interface.common.endpoint.ip']}")
	private String ifEndpointIp;

	/**
	 * 인터페이스 엔드포인트 PORT
	 */
	@Value(value = "#{propertiesFactoryBean['interface.common.endpoint.port']}")
	private String ifEndpointPort;

	/**
	 * OCR URL
	 */
	@Value(value = "#{propertiesFactoryBean['interface.ocr.url']}")
	private String ocrUrl;

	/**
	 * OCR SECRET KEY
	 */
	@Value(value = "#{propertiesFactoryBean['interface.ocr.secret-key']}")
	private String ocrSecretKey;

	/**
	 * 암호화 AES_KEY
	 */
	@Value(value = "#{propertiesFactoryBean['interface.encrypt.aes-key']}")
	private String encryptAesKey;

	/**
	 * 암호화 AES_IV
	 */
	@Value(value = "#{propertiesFactoryBean['interface.encrypt.aes-iv']}")
	private String encryptAesIv;

	
	@Override
	public IfMcCs002_O ifmccs002(String emnb, IfMcCs002_I cs002_I) throws JsonProcessingException, URISyntaxException {
		
		IfAdapter channelAdapter = new IfAdapter(emnb, activeProfile, ifEndpointUrl);

		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs002;

		IfTelegramHeader header = channelAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs002_O> outputEnvelop = channelAdapter.sendAndReceiveMessage(IfConstant.IfType.MCI, header, cs002_I, IfMcCs002_O.class);

        IfMcCs002_O cs002_O = outputEnvelop.getPayload();
		
		return cs002_O;
	}
	
	
	
	@Override
	public IfMcCs003_O ifmccs003(String emnb, IfMcCs003_I cs003_I) throws JsonProcessingException, URISyntaxException {
		
		IfAdapter channelAdapter = new IfAdapter(emnb, activeProfile, ifEndpointUrl);

		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs003;

		IfTelegramHeader header = channelAdapter.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs003_O> outputEnvelop = channelAdapter.sendAndReceiveMessage(IfConstant.IfType.MCI, header, cs003_I, IfMcCs003_O.class);

        IfMcCs003_O cs003_O = outputEnvelop.getPayload();
		
		return cs003_O;
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
