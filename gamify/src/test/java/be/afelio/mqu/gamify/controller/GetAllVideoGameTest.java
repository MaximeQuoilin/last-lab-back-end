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
import be.afelio.mqu.gamify.persistence.entities.EditorEntity;
import be.afelio.mqu.gamify.persistence.entities.GenreEntity;
import be.afelio.mqu.gamify.persistence.entities.PegiEntity;
import be.afelio.mqu.gamify.persistence.entities.PlatformEntity;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GetAllVideoGameTest {

	@Autowired TestRestTemplate restTemplate;
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
		assertEquals(actual, createVideogameDto());
		
	}
	
	List<VideogameDto> createVideogameDto() {
		ArrayList<VideogameDto> list = new ArrayList<VideogameDto>();
		
		List<PegiEntity> pegis = new ArrayList<PegiEntity>();
		PegiEntity pegi = new PegiEntity(1, "18+", "realy violent");
		pegis.add(pegi);
		
		List<PlatformEntity> platforms = new ArrayList<PlatformEntity>();
		platforms.add(new PlatformEntity(1,"PS4"));
		platforms.add(new PlatformEntity(2,"PC"));
		platforms.add(new PlatformEntity(3, "Xbox 360"));
		
		VideogameDto v = new VideogameDto(20,"Call of duty", "War game", null, new EditorEntity(2,"Activision"), new GenreEntity(1, "FPS"), pegis, platforms);
		list.add(v);
		
		pegi = new PegiEntity(2, "16+", "A little violent");
		pegis = new ArrayList<PegiEntity>();
		pegis.add(pegi);
		
		platforms = new ArrayList<PlatformEntity>();
		platforms.add(new PlatformEntity(2, "PC"));
		platforms.add(new PlatformEntity(1, "PS4"));
		
		v = new VideogameDto(21, "Fortnite", "Battle Royale",null, new EditorEntity(1, "Epic Games"), new GenreEntity(1, "FPS"), pegis, platforms);
		

		list.add(v);
		return list;
				
	}
}
