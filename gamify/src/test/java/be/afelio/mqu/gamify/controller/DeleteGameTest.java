package be.afelio.mqu.gamify.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.mqu.gamify.api.dto.ResponseDto;
import be.afelio.mqu.gamify.api.dto.ResponseDtoStatus;
import be.afelio.mqu.gamify.api.dto.classic.UserDto;
import be.afelio.mqu.gamify.api.dto.classic.VideogameDto;




@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class DeleteGameTest {
	
	@Autowired TestRestTemplate restTemplate;
	@Autowired JdbcTemplate jdbcTemplate;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void test() throws Exception {
		try {
			RequestEntity<VideogameDto> requestEntity
			= new RequestEntity<VideogameDto>(HttpMethod.DELETE, URI.create("/videogame/20"));
			ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
			assertEquals(200,response.getStatusCodeValue());
			String json = response.getBody();
			TypeReference<ResponseDto<Void>> type = new TypeReference<ResponseDto<Void>>() {};
			ResponseDto<Void> responseDto = mapper.readValue(json, type);
			
			assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
			assertTrue(checkGameDeleted());
			
		} finally {
			jdbcTemplate.update("INSERT INTO videogame (id, name, description, editor, genre) "
					+ "VALUES (20,'Call of duty', 'War game', 2, 1)");
			jdbcTemplate.update("INSERT INTO has_pegi (videogame_id, pegi_id) VALUES (20, 1)");
			jdbcTemplate.update("INSERT INTO existon (videogame_id, platform_id) VALUES (20,1)");
			jdbcTemplate.update("INSERT INTO existon (videogame_id, platform_id) VALUES (20,2)");
			jdbcTemplate.update("INSERT INTO existon (videogame_id, platform_id) VALUES (20,3)");
		}
		
		
	}

	boolean checkGameDeleted() {
		boolean deleted = false;
		try {
			jdbcTemplate.queryForObject("SELECT id FROM videogame WHERE name = 'Call of duty'", Integer.class);
		} catch (EmptyResultDataAccessException e) {
			deleted = true;
		}
		return deleted;
	}
	
	@Test
	public void testUnknownId() throws Exception {

		RequestEntity<UserDto> requestEntity 
		= new RequestEntity<UserDto>(HttpMethod.DELETE, URI.create("/videogame/100000"));
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		assertEquals(200, response.getStatusCodeValue());
		String json = response.getBody();
		TypeReference<ResponseDto<Void>> type = new TypeReference<ResponseDto<Void>>() {};
		ResponseDto<Void> responseDto = mapper.readValue(json, type);
		
		assertEquals(ResponseDtoStatus.FAILURE, responseDto.getStatus());
		assertEquals("videogame not found",responseDto.getMessage());
	}
}
