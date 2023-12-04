package com.gooroomee.gooroomeeadapter.service;

import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gooroomee.gooroomeeadapter.constant.IfConstant.IfSpec;
import com.gooroomee.gooroomeeadapter.dto.ifprovider.provider.OtpDto_I;
import com.gooroomee.gooroomeeadapter.dto.ifprovider.provider.common.IfProviderResponseCommonDto;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs999_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs999_O;

public interface GrmAdapterService {

	<I, O> O ifmccsCommon(String emnb, IfSpec ifSpec, I ifInputDto, Class<O> ifOutputDtoClass) throws JsonProcessingException, URISyntaxException;

	IfProviderResponseCommonDto<String> counsellingOtp(OtpDto_I dto_I) throws JsonMappingException, JsonProcessingException, URISyntaxException;

	IfMcCs999_O edmsRgstr(IfMcCs999_I edmsInput) throws URISyntaxException;

}