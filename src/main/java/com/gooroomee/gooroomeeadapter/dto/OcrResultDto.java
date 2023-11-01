package com.gooroomee.gooroomeeadapter.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OcrResultDto {
	private String uid;

	private ValidationResult validationResult;
	public static class ValidationResult {
		private String result;
	}

	private String inferResult;

	private IdCard idCard;

	public static class IdCard {
		private Result result;
		public static class Result {
			private String idtype;
			
			private List<Rois> rois;
			public static class Rois {
				
			}
		}
	}
}
