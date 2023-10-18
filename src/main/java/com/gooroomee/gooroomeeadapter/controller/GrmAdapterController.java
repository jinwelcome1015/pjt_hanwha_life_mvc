package com.gooroomee.gooroomeeadapter.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc002ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc002ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc003ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc003ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc005ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc005ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc006ReqDto;
import com.gooroomee.gooroomeeadapter.dto.client.Mvc006ResDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto;
import com.gooroomee.gooroomeeadapter.dto.client.common.ResponseDto.Result;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs005_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs005_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs006_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs006_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.common.IfTelegram;
import com.gooroomee.gooroomeeadapter.service.GrmAdapterService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class GrmAdapterController {

	@Autowired
	private GrmAdapterService gooroomeeAdapterService;

	@Autowired
	private ModelMapper modelMapper;

	// 진위확인 결과 조회
	@GetMapping(path = { "/intrf/trflCnfm" })
	public @ResponseBody ResponseDto<Mvc002ResDto> trflCnfm(@RequestBody Mvc002ReqDto reqDto)
			throws JsonProcessingException, URISyntaxException {

		IfMcCs002_I cs002_I = modelMapper.map(reqDto, IfMcCs002_I.class);

		String emnb = reqDto.getEmnb();

		IfMcCs002_O cs002_O = gooroomeeAdapterService.ifmccs002(emnb, cs002_I);

		Mvc002ResDto resDto = modelMapper.map(cs002_O, Mvc002ResDto.class);

		ResponseDto<Mvc002ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	// 신분증 스캔 후 처리
	@GetMapping(path = { "/intrf/itfcIdcdScan" })
	public @ResponseBody ResponseDto<Mvc003ResDto> itfcIdcdScan(@RequestBody Mvc003ReqDto reqDto)
			throws JsonProcessingException, URISyntaxException {

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

	// SSO대체로그인인증
	@PostMapping(path = { "/intrf/itfcUserCtfn" })
	public @ResponseBody ResponseDto<Mvc005ResDto> itfcUserCtfn(@RequestBody Mvc005ReqDto reqDto)
			throws JsonProcessingException, URISyntaxException {

		IfMcCs005_I cs005_I = modelMapper.map(reqDto, IfMcCs005_I.class);

		String emnb = reqDto.getEmnb();

		IfMcCs005_O cs005_O = gooroomeeAdapterService.ifmccs005(emnb, cs005_I);

		Mvc005ResDto resDto = modelMapper.map(cs005_O, Mvc005ResDto.class);

		ResponseDto<Mvc005ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	// 사원목록조회
	@PostMapping(path = { "/intrf/empeInqy" })
	public @ResponseBody ResponseDto<Mvc006ResDto> empeInqy(@RequestBody Mvc006ReqDto reqDto)
			throws JsonProcessingException, URISyntaxException {

		IfMcCs006_I cs006_I = modelMapper.map(reqDto, IfMcCs006_I.class);

		String emnb = reqDto.getEmnb();

		IfMcCs006_O cs006_O = gooroomeeAdapterService.ifmccs006(emnb, cs006_I);

		Mvc006ResDto resDto = modelMapper.map(cs006_O, Mvc006ResDto.class);

		ResponseDto<Mvc006ResDto> responseDto = new ResponseDto<>(Result.SUCCESS, HttpStatus.OK, resDto);

		return responseDto;
	}

	@GetMapping(path = "/test/doTest01")
	public @ResponseBody String doTest01() throws IOException {
		log.debug("doTest01");

		ClassPathResource resource = new ClassPathResource("mockData/res.log");
		Path path = Paths.get(resource.getURI());

		List<String> lines = Files.readAllLines(path);

		String delimiter = " ";

		/*
		for (String line : lines) {
			System.out.println(line);
		}
		*/
		return "doTest01";
	}

	public static void main(String[] args) throws IOException {
		ClassPathResource resource = new ClassPathResource("mockData/res.log");
		Path path = Paths.get(resource.getURI());

		List<String> lines = Files.readAllLines(path);

		List<String> filteredLines = new ArrayList<>();
		for (String line : lines) {
			if (line.contains("trnnNo") || line.contains("tscsRqstVal") || line.contains("postfixSysCode")
					|| line.contains("subTrnmSysType")) {
				continue;
			} else if (line.contains("msgeStackTrace") && line.endsWith(",")) {
				line = line.replaceAll(",$", "");
			}
			filteredLines.add(line);
		}

		String delimiter = " ";
		String jsonData = String.join(delimiter, filteredLines);

		String patternFrom = ".*(data=)";
		String patternTo = ", encrypt=false]\s*$";
		jsonData = jsonData.replaceAll(patternFrom, "");
		jsonData = jsonData.replaceAll(patternTo, "");

		System.out.println(jsonData);

		ObjectMapper objectMapper = new ObjectMapper();

		JavaType javaType = TypeFactory.defaultInstance().constructParametricType(IfTelegram.class, IfMcCs005_O.class);
		IfTelegram<IfMcCs005_O> responseTelegram = null;
		responseTelegram = objectMapper.readValue(jsonData, javaType);

		System.out.println(responseTelegram);
	}
}
