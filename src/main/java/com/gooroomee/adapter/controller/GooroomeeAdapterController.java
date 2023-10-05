package com.gooroomee.adapter.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gooroomee.adapter.constant.TeleConstant;
import com.gooroomee.adapter.dto.common.ResponseDto;
import com.gooroomee.adapter.dto.common.ResponseDto.Result;
import com.gooroomee.adapter.dto.io.IfMcCs001_I;
import com.gooroomee.adapter.dto.io.IfMcCs001_O;
import com.gooroomee.adapter.dto.io.IfMcCs002_I;
import com.gooroomee.adapter.dto.io.IfMcCs002_O;
import com.gooroomee.adapter.dto.io.IfMcCs012_I;
import com.gooroomee.adapter.dto.io.IfMcCs012_O;
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

		return null;
	}

	// 간편인증 상태 조회
	@GetMapping(path = { "/intrf/ifmccs012" })
	public @ResponseBody ResponseDto<IfMcCs012_O> ifmccs012(@RequestBody IfMcCs012_I req_ifMcCs012_I) throws JsonProcessingException {

		gooroomeeAdapterService.ifmccs012(req_ifMcCs012_I);
		
		return null;
	}

	@RequestMapping(path = "test1", method = RequestMethod.GET)
	@ResponseBody
	public String doTest1() throws URISyntaxException {

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
