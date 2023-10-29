package com.gooroomee.gooroomeeadapter.service;

import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonProcessingException;
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

public interface GrmAdapterService {

	IfMcCs002_O ifmccs002(String emnb, IfMcCs002_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs003_O ifmccs003(String emnb, IfMcCs003_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs005_O ifmccs005(String emnb, IfMcCs005_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs006_O ifmccs006(String emnb, IfMcCs006_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs007_O ifmccs007(String emnb, IfMcCs007_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs008_O ifmccs008(String emnb, IfMcCs008_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs009_O ifmccs009(String emnb, IfMcCs009_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs010_O ifmccs010(String emnb, IfMcCs010_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs011_O ifmccs011(String emnb, IfMcCs011_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs012_O ifmccs012(String emnb, IfMcCs012_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs016_O ifmccs016(String emnb, IfMcCs016_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs003_O ifmccs003(String emnb, IfSpec ifSpec, IfMcCs003_I ifInputDto, Class<IfMcCs003_O> ifOutputDtoClass)
			throws JsonProcessingException, URISyntaxException;

}