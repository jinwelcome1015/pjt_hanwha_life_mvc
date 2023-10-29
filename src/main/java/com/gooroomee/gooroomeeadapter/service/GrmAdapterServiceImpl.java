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
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs007_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs007_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs008_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs008_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs009_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs009_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs010_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs010_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs011_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs011_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs012_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs012_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs016_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs016_O;
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
//	@Value(value = "#{propertiesFactoryBean['interface.endpoint.url']}")
	@Value(value = "${interface.endpoint.url}")
	private String ifEndpointUrl;

	/** 인터페이스 엔드포인트 IP */
//	@Value(value = "#{propertiesFactoryBean['interface.endpoint.ip']}")
	@Value(value = "${interface.endpoint.ip}")
	private String ifEndpointIp;

	/** 인터페이스 엔드포인트 PORT */
//	@Value(value = "#{propertiesFactoryBean['interface.endpoint.port']}")
	@Value(value = "${interface.endpoint.port}")
	private String ifEndpointPort;

	/** OCR URL */
//	@Value(value = "#{propertiesFactoryBean['interface.ocr.url']}")
	@Value(value = "${interface.ocr.url}")
	private String ocrUrl;

	/** OCR SECRET KEY */
//	@Value(value = "#{propertiesFactoryBean['interface.ocr.secret-key']}")
	@Value(value = "${interface.ocr.secret-key}")
	private String ocrSecretKey;

	/** 암호화 AES_KEY */
//	@Value(value = "#{propertiesFactoryBean['interface.encrypt.aes-key']}")
	@Value(value = "${interface.encrypt.aes-key}")
	private String encryptAesKey;

	/** 암호화 AES_IV */
//	@Value(value = "#{propertiesFactoryBean['interface.encrypt.aes-iv']}")
	@Value(value = "${interface.encrypt.aes-iv}")
	private String encryptAesIv;
	
	@Override
	public IfMcCs002_O ifmccs002(String emnb, IfMcCs002_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs002;

		Class<IfMcCs002_O> ifOutputDtoClass = IfMcCs002_O.class;
		
		IfUtil ifUtil = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs002_O> outputEnvelop = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);

        IfMcCs002_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	
	
	/*
	@Override
	public IfMcCs003_O ifmccs003(String emnb, IfMcCs003_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs003;
	
		Class<IfMcCs003_O> ifOutputDtoClass = IfMcCs003_O.class;
		
		IfUtil ifUtil = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
	    IfTelegram<IfMcCs003_O> outputEnvelop = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);
	
	    IfMcCs003_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	*/
	
	
	@Override
	public IfMcCs003_O ifmccs003(String emnb, IfSpec ifSpec, IfMcCs003_I ifInputDto, Class<IfMcCs003_O> ifOutputDtoClass) throws JsonProcessingException, URISyntaxException {
		
//		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs003;
//	
//		Class<IfMcCs003_O> ifOutputDtoClass = IfMcCs003_O.class;
		
		IfUtil ifUtil = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
	    IfTelegram<IfMcCs003_O> outputEnvelop = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);
	
	    IfMcCs003_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	
	
	
	@Override
	public IfMcCs005_O ifmccs005(String emnb, IfMcCs005_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs005;

		Class<IfMcCs005_O> ifOutputDtoClass = IfMcCs005_O.class;
		
		IfUtil ifUtil = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs005_O> outputEnvelop = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);

        IfMcCs005_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	
	
	@Override
	public IfMcCs006_O ifmccs006(String emnb, IfMcCs006_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs006;

		Class<IfMcCs006_O> ifOutputDtoClass = IfMcCs006_O.class;
		
		IfUtil ifUtil = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs006_O> outputEnvelop = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);

        IfMcCs006_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	
	
	@Override
	public IfMcCs007_O ifmccs007(String emnb, IfMcCs007_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs007;

		Class<IfMcCs007_O> ifOutputDtoClass = IfMcCs007_O.class;
		
		IfUtil ifUtil = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs007_O> outputEnvelop = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);

        IfMcCs007_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	
	
	@Override
	public IfMcCs008_O ifmccs008(String emnb, IfMcCs008_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs008;

		Class<IfMcCs008_O> ifOutputDtoClass = IfMcCs008_O.class;
		
		IfUtil ifUtil = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs008_O> outputEnvelop = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);

        IfMcCs008_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}



	@Override
	public IfMcCs009_O ifmccs009(String emnb, IfMcCs009_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs009;

		Class<IfMcCs009_O> ifOutputDtoClass = IfMcCs009_O.class;
		
		IfUtil ifUtil = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs009_O> outputEnvelop = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);

        IfMcCs009_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	
	
	@Override
	public IfMcCs010_O ifmccs010(String emnb, IfMcCs010_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs010;

		Class<IfMcCs010_O> ifOutputDtoClass = IfMcCs010_O.class;
		
		IfUtil ifUtil = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs010_O> outputEnvelop = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);

        IfMcCs010_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	
	
	@Override
	public IfMcCs011_O ifmccs011(String emnb, IfMcCs011_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs011;

		Class<IfMcCs011_O> ifOutputDtoClass = IfMcCs011_O.class;
		
		IfUtil ifUtil = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs011_O> outputEnvelop = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);

        IfMcCs011_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	
	@Override
	public IfMcCs012_O ifmccs012(String emnb, IfMcCs012_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs012;

		Class<IfMcCs012_O> ifOutputDtoClass = IfMcCs012_O.class;
		
		IfUtil ifUtil = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs012_O> outputEnvelop = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);

        IfMcCs012_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	
	@Override
	public IfMcCs016_O ifmccs016(String emnb, IfMcCs016_I ifInputDto) throws JsonProcessingException, URISyntaxException {
		IfSpec ifSpec = IfConstant.IfSpec.IfMcCs016;

		Class<IfMcCs016_O> ifOutputDtoClass = IfMcCs016_O.class;
		
		IfUtil ifUtil = new IfUtil(restTemplate, emnb, activeProfile, ifEndpointUrl);
		
		IfTelegramHeader header = ifUtil.createHeader(ifSpec.getItfcId(), ifSpec.getRcveSrvcId(), ifSpec.getRcveSysCode()); 
		
        IfTelegram<IfMcCs016_O> outputEnvelop = ifUtil.sendAndReceiveTelegram(IfConstant.IfType.MCI, header, ifInputDto, ifOutputDtoClass);

        IfMcCs016_O ifOutputDto = outputEnvelop.getPayload();
		
		return ifOutputDto;
	}
	
}
