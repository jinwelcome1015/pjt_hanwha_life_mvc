package com.gooroomee.backbone.external.dto.ifconsumer.server.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class IfTelegram<P> {
	private IfTelegramHeader header;
	private P payload;
}
