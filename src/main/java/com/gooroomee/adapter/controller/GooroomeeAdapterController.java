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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.gooroomee.adapter.constant.TeleConstant;
import com.gooroomee.adapter.dto.common.ResponseDto;
import com.gooroomee.adapter.dto.common.ResponseDto.Result;

@Controller
public class GooroomeeAdapterController {
	
	@Value(value = "#{propertiesFactoryBean['interface.endpoint.url']}")
	String targetUrl;
	
	
	@Autowired
	public RestTemplateBuilder restTemplateBuilder; 

	@RequestMapping(path = "test1", method = RequestMethod.GET)
	@ResponseBody
	public String doTest1() throws URISyntaxException {
		
		System.out.println("targetUrl : " + targetUrl);
		
		
		URI url = new URI("https://jsonplaceholder.typicode.com/posts");
		
		RestTemplate restTemplate = restTemplateBuilder.build();
		
		String result = restTemplate.getForObject(url, String.class);
		
		System.out.println(result);
		
		return result;
	}
	
	@GetMapping(path = "/test2")
	public @ResponseBody String doTest2() {
		System.out.println("targetUrl : " + targetUrl);
		
		return "test";
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
