package com.gooroomee.adapter.util;

import com.gooroomee.adapter.constant.TeleConstant.IfType;

public class DefaultRestApiClient {
	private String interfaceType; 
	private String endpointUrl;

	public DefaultRestApiClient(IfType ifType, String endpointUrl) {
		this.interfaceType = ifType.getValue();
		this.endpointUrl = endpointUrl;
	}

}
