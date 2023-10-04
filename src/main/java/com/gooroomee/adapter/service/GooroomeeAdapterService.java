package com.gooroomee.adapter.service;

import org.springframework.beans.factory.annotation.Value;

public class GooroomeeAdapterService {
	
	@Value(value = "#{propertiesFactoryBean['interface.endpoint.url']}")
	String targetUrl;
	
	
	
}
