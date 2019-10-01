package be.afelio.mqu.gamify.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import be.afelio.mqu.gamify.api.dto.classic.UserDto;
import be.afelio.mqu.gamify.test_utils.Utils;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GetAllOwnersForOneGameTest {
	
	@Autowired TestRestTemplate restTemplate;
	@Autowired Utils utils;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testOwnersOfGameId21() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity("/videogame/21/owners", String.class); 
		assertEquals(200, response.getStatusCodeValue());
		
		String json = response.getBody();
		TypeReference<ResponseDto<List<UserDto>>> type = new TypeReference<ResponseDto<List<UserDto>>>() {};
		ResponseDto<List<UserDto>> responseDto = mapper.readValue(json, type);
		
		assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
		List<UserDto> actual = responseDto.getPayload();
		assertEquals(actual, utils.createListOwnersOfVideogame(21));
	}
	
	@Test
	public void testGameWithoutOwner() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity("/videogame/25/owners", String.class); 
		assertEquals(200, response.getStatusCodeValue());
		
		String json = response.getBody();
		TypeReference<ResponseDto<List<UserDto>>> type = new TypeReference<ResponseDto<List<UserDto>>>() {};
		ResponseDto<List<UserDto>> responseDto = mapper.readValue(json, type);
		
		assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus()); 
		List<UserDto> actual = responseDto.getPayload();
		assertTrue(actual.isEmpty());
		
	}
	
	

}
