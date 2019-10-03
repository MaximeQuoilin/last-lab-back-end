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
import be.afelio.mqu.gamify.api.dto.total.UserDto;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class DeleteUserTest {

	@Autowired TestRestTemplate restTemplate;
	@Autowired JdbcTemplate jdbcTemplate;
	
	ObjectMapper mapper = new ObjectMapper();
	
	
	@Test
	public void testDeleteOneWithoutGame() throws Exception {
		
		try {
			
			RequestEntity<UserDto> requestEntity 
			= new RequestEntity<UserDto>(HttpMethod.DELETE, URI.create("/user/3"));
			ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
			assertEquals(200, response.getStatusCodeValue());
			String json = response.getBody();
			TypeReference<ResponseDto<Void>> type = new TypeReference<ResponseDto<Void>>() {};
			ResponseDto<Void> responseDto = mapper.readValue(json, type);
			
			assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
			assertTrue(checkUserDeleted());
			
		} finally {
			jdbcTemplate.update("INSERT INTO tuser (id, username, password,email) "
					+ "VALUES (3,'maximus', 'decimus', 'maximus@coliseum.rom')");
		}
		
		
	}

	boolean checkUserDeleted() {
		boolean deleted = false;
		try {
			jdbcTemplate.queryForObject("SELECT id FROM tuser WHERE username = 'maximus'", Integer.class);
			// si query ne renvoie rien => exception => catchee => deleted passe a true
			// sinon deleted reste a false
		} catch (EmptyResultDataAccessException e) {
			deleted = true;
		}
		return deleted;
	}
	
	@Test
	public void testUnknownId() throws Exception {

		RequestEntity<UserDto> requestEntity 
		= new RequestEntity<UserDto>(HttpMethod.DELETE, URI.create("/user/100000"));
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		assertEquals(200, response.getStatusCodeValue());
		String json = response.getBody();
		TypeReference<ResponseDto<Void>> type = new TypeReference<ResponseDto<Void>>() {};
		ResponseDto<Void> responseDto = mapper.readValue(json, type);
		
		assertEquals(ResponseDtoStatus.FAILURE, responseDto.getStatus());
		assertEquals("user not found",responseDto.getMessage());
	}
	
}
