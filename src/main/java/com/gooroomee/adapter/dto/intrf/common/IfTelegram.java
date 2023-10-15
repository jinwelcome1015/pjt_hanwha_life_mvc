package com.gooroomee.adapter.dto.intrf.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class SimpleMessageEnvelop<P> {
	private HlicpMessageHeader header;
	private P payload;
}
