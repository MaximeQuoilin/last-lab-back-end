package be.afelio.mqu.gamify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import be.afelio.mqu.gamify.api.dto.ResponseDto;
import be.afelio.mqu.gamify.api.dto.ResponseDtoStatus;
import be.afelio.mqu.gamify.api.dto.VideogameDto;
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
}
