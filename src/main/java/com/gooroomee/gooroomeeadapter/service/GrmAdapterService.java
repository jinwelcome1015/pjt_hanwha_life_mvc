package com.gooroomee.gooroomeeadapter.service;

import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonProcessingException;
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

public interface GrmAdapterService {

	IfMcCs002_O ifmccs002(String emnb, IfMcCs002_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs003_O ifmccs003(String emnb, IfMcCs003_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs005_O ifmccs005(String emnb, IfMcCs005_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs006_O ifmccs006(String emnb, IfMcCs006_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs007_O ifmccs007(String emnb, IfMcCs007_I ifInputDto) throws JsonProcessingException, URISyntaxException;

	IfMcCs008_O ifmccs008(String emnb, IfMcCs008_I ifInputDto) throws JsonProcessingException, URISyntaxException;

}