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
import be.afelio.mqu.gamify.api.dto.classic.EditorDto;
import be.afelio.mqu.gamify.api.dto.classic.GenreDto;
import be.afelio.mqu.gamify.api.dto.classic.PegiDto;
import be.afelio.mqu.gamify.api.dto.classic.PlatformDto;
import be.afelio.mqu.gamify.api.dto.classic.VideogameDto;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GetAllVideoGameTest {

	@Autowired TestRestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void test() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity("/videogame/all", String.class);
		//assertEquals(200, response.getStatusCode());
		
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
		List<PegiDto> pegis = new ArrayList<PegiDto>();
		PegiDto pegiDto = new PegiDto(1, "18+", "realy violent");
		pegis.add(pegiDto);
		List<PlatformDto> platforms = new ArrayList<PlatformDto>();
		PlatformDto platformDto = new PlatformDto(1,"PS4");
		platforms.add(platformDto);
		platformDto = new PlatformDto(2,"PC");
		platforms.add(platformDto);
		platformDto = new PlatformDto(3, "Xbox 360");
		platforms.add(platformDto);
		VideogameDto v = new VideogameDto();
		v.setDescription("War game");
		EditorDto editorDto = new EditorDto(2,"Activision");
		v.setEditor(editorDto);
		GenreDto genreDto = new GenreDto(1, "FPS");
		v.setGenre(genreDto);
		v.setId(20);
		v.setName("Call of duty");
		v.setPegis(pegis);
		v.setPlatforms(platforms);
		list.add(v);
		v.setDescription("A little violent");
		editorDto = new EditorDto(1,"Epic Games");
		v.setEditor(editorDto);
		genreDto = new GenreDto(1, "FPS");
		v.setGenre(genreDto);
		v.setId(21);
		v.setName("Fortnite");
		pegis = new ArrayList<PegiDto>();
		pegiDto = new PegiDto(2, "16+", "A little violent");
		pegis.add(pegiDto);
		v.setPegis(pegis);
		platforms = new ArrayList<PlatformDto>();
		platformDto = new PlatformDto(2, "PC");
		platforms.add(platformDto);
		platformDto = new PlatformDto(1, "PS4");
		platforms.add(platformDto);
		v.setPlatforms(platforms);
		list.add(v);
		return list;
				
	}
}
