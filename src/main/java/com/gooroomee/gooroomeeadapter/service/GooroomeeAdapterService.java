package com.gooroomee.gooroomeeadapter.service;

import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs002_O;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_I;
import com.gooroomee.gooroomeeadapter.dto.intrf.IfMcCs003_O;

public interface GooroomeeAdapterService {

	IfMcCs002_O ifmccs002(String emnb, IfMcCs002_I cs002_I) throws JsonProcessingException, URISyntaxException;

	IfMcCs003_O ifmccs003(String emnb, IfMcCs003_I cs003_I) throws JsonProcessingException, URISyntaxException;

}