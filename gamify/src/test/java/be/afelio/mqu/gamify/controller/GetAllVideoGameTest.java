package be.afelio.mqu.gamify.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.mqu.gamify.api.dto.ResponseDto;
import be.afelio.mqu.gamify.api.dto.ResponseDtoStatus;
import be.afelio.mqu.gamify.api.dto.classic.VideogameDto;
import be.afelio.mqu.gamify.test_utils.Utils;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GetAllVideoGameTest {

	@Autowired TestRestTemplate restTemplate;
	@Autowired Utils utils;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void test() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity("/videogame/all", String.class);
		assertEquals(200, response.getStatusCodeValue());
		
		String json = response.getBody();
		TypeReference<ResponseDto<List<VideogameDto>>> type = new TypeReference<ResponseDto<List<VideogameDto>>>() {};
		ResponseDto<List<VideogameDto>> responseDto = mapper.readValue(json, type);
		
		assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
		
		List<VideogameDto> actual = responseDto.getPayload();
		assertTrue(actual.size() == 2);
		List<VideogameDto> expected = utils.createListVideoGameDto();
		Collections.sort(actual, (a, b) -> a.getName().compareTo(b.getName()));
		Collections.sort(expected, (a, b) -> a.getName().compareTo(b.getName()));
		assertEquals(actual, expected);
		
	}
}
