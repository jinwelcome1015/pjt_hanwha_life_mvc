package test;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gooroomee.adapter.dto.client.common.ResponseDto;
import com.gooroomee.adapter.dto.client.common.ResponseDto.Result;

public class Test {

	public static class TestReqDto {
		private String name;
		private String job;

		public TestReqDto(String name, String job) {
			super();
			this.name = name;
			this.job = job;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getJob() {
			return job;
		}

		public void setJob(String job) {
			this.job = job;
		}

		@Override
		public String toString() {
			return "TestReqDto [name=" + name + ", job=" + job + "]";
		}
	}

	public static class TestResDto {
		private String name;
		private String job;
		private String id;
		private String createdAt;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getJob() {
			return job;
		}

		public void setJob(String job) {
			this.job = job;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}

		@Override
		public String toString() {
			return "TestResDto [name=" + name + ", job=" + job + ", id=" + id + ", createdAt=" + createdAt
					+ ", getName()=" + getName() + ", getJob()=" + getJob() + ", getId()=" + getId()
					+ ", getCreatedAt()=" + getCreatedAt() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
					+ ", toString()=" + super.toString() + "]";
		}
	}
	
	public void doTestHttp() throws JsonProcessingException {
		URI uri = UriComponentsBuilder.fromUriString("https://reqres.in/").path("/api/users").build().toUri();

		TestReqDto testReqDto = new TestReqDto("morpheus", "leader");

//		new HttpEntity<T>(body, headers)
		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.add(headerName, headerValue);
		HttpEntity<TestReqDto> requestEntity = new HttpEntity<TestReqDto>(testReqDto, httpHeaders);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<TestResDto> exchange = restTemplate.exchange(uri, HttpMethod.POST, requestEntity,
				TestResDto.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String writeValueAsString = objectMapper.writeValueAsString(exchange);

		System.out.println(writeValueAsString);
//		exchange.getBody();
	}
	
	public void doResult() throws JsonProcessingException {
		ResponseDto<TestResDto> responseDto = new ResponseDto<TestResDto>(Result.SUCCESS, HttpStatus.OK, new TestResDto());
		ObjectMapper objectMapper = new ObjectMapper();
		String writeValueAsString = objectMapper.writeValueAsString(responseDto);
		System.out.println(writeValueAsString);
	}

	public static void main(String[] args) {
		Test test = new Test();
		try {
			test.doResult();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
}
