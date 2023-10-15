package com.gooroomee.adapter.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gooroomee.adapter.dto.client.Mvc003ReqDto;
import com.gooroomee.adapter.dto.client.Mvc003ResDto;
import com.gooroomee.adapter.dto.client.common.ResponseDto;
import com.gooroomee.adapter.dto.client.common.ResponseDto.Result;
import com.gooroomee.adapter.dto.intrf.IfMcCs001_I;
import com.gooroomee.adapter.dto.intrf.IfMcCs001_O;
import com.gooroomee.adapter.dto.intrf.IfMcCs002_I;
import com.gooroomee.adapter.dto.intrf.IfMcCs002_O;
import com.gooroomee.adapter.dto.intrf.IfMcCs003_I;
import com.gooroomee.adapter.dto.intrf.IfMcCs003_O;
import com.gooroomee.adapter.service.GooroomeeAdapterService;

@Controller
public class GooroomeeAdapterController {

	@Autowired
	public RestTemplateBuilder restTemplateBuilder;

	@Autowired
	public GooroomeeAdapterService gooroomeeAdapterService;

	// 신분증OCR요청
	@GetMapping(path = { "/intrf/ifmccs001" })
	public @ResponseBody ResponseDto<IfMcCs001_O> ifmccs001(@RequestBody IfMcCs001_I req_ifMcCs001_I) {

		return null;
	}

	// 진위확인 결과 조회
	@GetMapping(path = { "/intrf/ifmccs002" })
	public @ResponseBody ResponseDto<IfMcCs002_O> ifmccs002(@RequestBody IfMcCs002_I req_ifMcCs002_I) {
		
		System.out.println("========================1");

		System.out.println(req_ifMcCs002_I);
		
		System.out.println("========================2");
		
		return null;
	}

	// 신분증 스캔 후 처리
	@GetMapping(path = { "/intrf/ifmccs003" })
	public @ResponseBody ResponseDto<Mvc003ResDto> ifmccs003(@RequestBody Mvc003ReqDto reqDto) throws JsonProcessingException, URISyntaxException {
		
		IfMcCs003_I cs003_I = new IfMcCs003_I();
		cs003_I.setCustId(reqDto.getCustId());
		cs003_I.setPushRcvrEmnb(reqDto.getPushRcvrEmnb());
		cs003_I.setCsnsYn(reqDto.getCsnsYn());
		
		String emnb = reqDto.getEmnb();
		
		IfMcCs003_O cs003_O = gooroomeeAdapterService.ifmccs003(emnb, cs003_I);
		
		Mvc003ResDto resDto = new Mvc003ResDto();
		resDto.setPrcsSucsYn(cs003_O.getPrcsSucsYn());
		
		ResponseDto<Mvc003ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);
		
		return responseDto;
	}

	/*
	// 간편인증 상태 조회
	@GetMapping(path = { "/intrf/ifmccs012" })
	public @ResponseBody ResponseDto<IfMcCs012_O> ifmccs012(@RequestBody IfMcCs012_I req_ifMcCs012_I)
			throws JsonProcessingException {
	
		gooroomeeAdapterService.ifmccs012(req_ifMcCs012_I);
	
		return null;
	}
	*/
	
	@GetMapping(path = "/test3")
	public @ResponseBody String doTest1() throws URISyntaxException {

		URI url = new URI("https://jsonplaceholder.typicode.com/posts");

		RestTemplate restTemplate = restTemplateBuilder.build();

		String result = restTemplate.getForObject(url, String.class);

		System.out.println(result);

		return result;
	}

	@GetMapping(path = "/test3")
	public @ResponseBody ResponseDto doTest3() {

		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();

		map1.put("1", map2);
		map2.put("2", map3);
		map3.put("3", "a");

		ResponseDto<Map> responseDto = new ResponseDto<Map>(Result.SUCCESS, HttpStatus.OK, map1);

		return responseDto;
	}
}
