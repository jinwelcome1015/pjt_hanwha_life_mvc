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
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gooroomee.adapter.dto.client.Mvc003ResDto;
import com.gooroomee.adapter.dto.client.Mvc006ResDto;
import com.gooroomee.adapter.dto.client.common.ResponseDto;
import com.gooroomee.adapter.dto.client.common.ResponseDto.Result;
import com.gooroomee.adapter.dto.intrf.IfMcCs003_I;
import com.gooroomee.adapter.dto.intrf.common.IfTelegramHeader;
import com.gooroomee.adapter.dto.intrf.common.IfTelegram;

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
		ResponseDto<TestResDto> responseDto = new ResponseDto<TestResDto>(Result.SUCCESS, HttpStatus.OK,
				new TestResDto());
		ObjectMapper objectMapper = new ObjectMapper();
		String writeValueAsString = objectMapper.writeValueAsString(responseDto);
		System.out.println(writeValueAsString);
	}

	/*
	public static void main(String[] args) {
		Test test = new Test();
		try {
			test.doResult();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	*/
	/*
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
	
		IfMcCs003_I cs003_I = new IfMcCs003_I();
		cs003_I.setCsnsYn("Y");
		cs003_I.setCustId("ABC");
		cs003_I.setPushRcvrEmnb("123");
	
		HlicpMessageHeader hlicpMessageHeader = new HlicpMessageHeader();
		hlicpMessageHeader.setBaseCnty("baseCnty");
		hlicpMessageHeader.setBaseCrny("baseCrny");
	
		SimpleMessageEnvelop<Object> simpleMessageEnvelop = new SimpleMessageEnvelop<>();
		simpleMessageEnvelop.setHeader(hlicpMessageHeader);
		simpleMessageEnvelop.setPayload(cs003_I);
	
		String valueAsString = objectMapper.writeValueAsString(simpleMessageEnvelop);
	
		System.out.println(valueAsString);
		System.out.println("============================================");
	
		SimpleMessageEnvelop<IfMcCs003_I> responseEnvelop = null;
	//		JavaType javaType = TypeFactory.defaultInstance().constructParametricType(SimpleMessageEnvelop.class, IfMcCs003_I.class);
		TypeReference<SimpleMessageEnvelop<IfMcCs003_I>> javaType = new TypeReference<SimpleMessageEnvelop<IfMcCs003_I>>() {};
		
		responseEnvelop = objectMapper.readValue(valueAsString, javaType);
	
		String valueAsString2 = objectMapper.writeValueAsString(responseEnvelop);
		System.out.println(valueAsString2);
	}
	*/
	
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		String origin = "{ \"empeInfoList\": [ { \"empeDvsnCode\": \"2\", \"orgnNm\": \"다이렉트개발팀\", \"orgnCode\": \"00583\", \"empeNm\": \"이혜란\", \"tnofDvsnCode\": \"P\", \"clpsCode\": \"12\", \"emnb\": \"2230201\", \"wkisYn\": \"Y\", \"rrnoInidSuid\": 1000000000028402080, \"custId\": \"4041297134\", \"prsnCorpSaleDvsnCode\": \"1\", \"pstnCode\": \"12\", \"rsofCode\": \"BC1\" } ] }";
		
		Mvc006ResDto resDto = objectMapper.readValue(origin, Mvc006ResDto.class);
		
		String writeValueAsString = objectMapper.writeValueAsString(resDto);
		
		IfTelegramHeader hlicpMessageHeader = new IfTelegramHeader();
		hlicpMessageHeader.setBaseCnty("baseCnty");
		hlicpMessageHeader.setBaseCrny("baseCrny");
		
		IfTelegram<Object> simpleMessageEnvelop = new IfTelegram<>();
		simpleMessageEnvelop.setHeader(hlicpMessageHeader);
		simpleMessageEnvelop.setPayload(resDto);
		
		String valueAsString = objectMapper.writeValueAsString(simpleMessageEnvelop);
		
		System.out.println(valueAsString);
		System.out.println("============================================");
		
		IfTelegram<IfMcCs003_I> responseEnvelop = null;
		JavaType javaType = TypeFactory.defaultInstance().constructParametricType(IfTelegram.class,
				Mvc006ResDto.class);
		responseEnvelop = objectMapper.readValue(valueAsString, javaType);
		
		String valueAsString2 = objectMapper.writeValueAsString(responseEnvelop);
		System.out.println(valueAsString2);
		
	}
	
}
