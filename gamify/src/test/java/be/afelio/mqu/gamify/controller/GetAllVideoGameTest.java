package be.afelio.mqu.gamify.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GetAllVideoGameTest {

	@Autowired TestRestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void test() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity("/videogame/all", String.class);
		assertEquals(200, response.getStatusCode());
		
		String json = response.getBody();
		TypeReference<ResponseDto<List<VideogameDto>>> type = new TypeReference<ResponseDto<List<VideogameDto>>>() {};
		ResponseDto<List<VideogameDto>> responseDto = mapper.readValue(json, type);
		
		assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
		
		List<VideogameDto> actual = responseDto.getPayload();
		assertTrue(actual.size() == 2);
		assertEquals(actual, createVideogameDto());
		
	}
	
	List<VideogameDto> createVideogameDto() {
		ArrayList<VideogameDto> list = new ArrayList<VideogameDto>();
//		list.add(new VideogameDto(21, "Fortnite", "Battle Royale", 1, 1, pegis, new ));
//		list.add(new VideogameDto(20, "Call of duty", "War game", 2, "FPS", "18+", "PS4"));
		return list;
				
	}
}
