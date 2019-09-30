package be.afelio.mqu.gamify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.afelio.mqu.gamify.api.dto.ResponseDto;
import be.afelio.mqu.gamify.api.dto.ResponseDtoStatus;
import be.afelio.mqu.gamify.api.dto.classic.UserDto;
import be.afelio.mqu.gamify.api.dto.classic.VideogameDto;
import be.afelio.mqu.gamify.api.dto.simple.UserSimpleDto;
import be.afelio.mqu.gamify.persistence.ApplicationRepository;


@Controller
@RequestMapping(value="videogame")
public class VideogameController {
	@Autowired ApplicationRepository repository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="all", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<VideogameDto>>> findAll() {
		
		ResponseDto<List<VideogameDto>> dto = null;
		try {
			List<VideogameDto> videogames = repository.findAllVideogames();
			if (videogames == null) {
				dto = new ResponseDto<List<VideogameDto>>(ResponseDtoStatus.FAILURE, " videogames not found");
			} else {
				dto = new ResponseDto<List<VideogameDto>>(ResponseDtoStatus.SUCCESS, videogames.size() +  "videogames found");
				dto.setPayload(videogames);
			}
		} catch(Exception e) {
			dto = new ResponseDto<List<VideogameDto>>(ResponseDtoStatus.FAILURE, "unexpected exception");
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(dto);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="/{id}/users", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<UserSimpleDto>>> findAllUsersForOneVideoGame(
			@PathVariable("id") Integer id) {
		
		ResponseDto<List<UserSimpleDto>> dto = null;
		try {
			List<UserSimpleDto> users = repository.findAllUsersForOneVideoGame(id);
			if (users == null) {
				dto = new ResponseDto<List<UserSimpleDto>>(ResponseDtoStatus.FAILURE, "users not found");
			} else {
				dto = new ResponseDto<List<UserSimpleDto>>(ResponseDtoStatus.SUCCESS, users.size() +  " users found");
				dto.setPayload(users);
			}
		} catch(Exception e) {
			dto = new ResponseDto<List<UserSimpleDto>>(ResponseDtoStatus.FAILURE, "unexpected exception");
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(dto);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<VideogameDto>> findOneVideoGameById(
			@PathVariable("id") Integer id) {
		
		ResponseDto<VideogameDto> dto = null;
		try {
			VideogameDto users = repository.findOneVideoGameById(id);
			if (users == null) {
				dto = new ResponseDto<VideogameDto>(ResponseDtoStatus.FAILURE, "videogame not found");
			} else {
				dto = new ResponseDto<VideogameDto>(ResponseDtoStatus.SUCCESS, " videogame found");
				dto.setPayload(users);
			}
		} catch(Exception e) {
			dto = new ResponseDto<VideogameDto>(ResponseDtoStatus.FAILURE, "unexpected exception");
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(dto);
	}
}
