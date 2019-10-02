package be.afelio.mqu.gamify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import be.afelio.mqu.gamify.api.dto.ResponseDto;
import be.afelio.mqu.gamify.api.dto.ResponseDtoStatus;
import be.afelio.mqu.gamify.api.dto.classic.VideogameDto;
import be.afelio.mqu.gamify.api.dto.create.CreateVideogameDto;
import be.afelio.mqu.gamify.api.exceptions.duplicate.DuplicateVideogameException;
import be.afelio.mqu.gamify.api.exceptions.notFound.VideogameNotFoundException;
import be.afelio.mqu.gamify.repositories.interfaces.VideogameApplicationRepositoryInterface;

@Controller
@RequestMapping(value="videogame")
public class VideogameController {
	
	@Autowired VideogameApplicationRepositoryInterface repository;
		
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<Void>>  createUser(
			@RequestBody CreateVideogameDto createVideogameDto
			) {
		ResponseDto<Void> dto = null;
		
		try {
			repository.createVideogame(createVideogameDto);
			dto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "videogame created");
		} 
		catch(DuplicateVideogameException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "Ce jeu video existe déjà !");
		}
		catch(Exception e) {
			e.printStackTrace();
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
		}
		
		return ResponseEntity.ok(dto);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="all", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<VideogameDto>>> findAll() {
		
		ResponseDto<List<VideogameDto>> dto = null;
		try {
			List<VideogameDto> videogames = repository.findAllVideogames();
			if (videogames.isEmpty()) {
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
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<VideogameDto>> findOneVideoGameById(
			@PathVariable("id") Integer id) {
		
		ResponseDto<VideogameDto> dto = null;
		try {
			VideogameDto users = repository.findOneVideoGameById(id);
			if (users == null) {
				dto = new ResponseDto<VideogameDto>(ResponseDtoStatus.FAILURE, "videogame not found");
			} else {
				dto = new ResponseDto<VideogameDto>(ResponseDtoStatus.SUCCESS, "videogame found");
				dto.setPayload(users);
			}
		} catch(Exception e) {
			dto = new ResponseDto<VideogameDto>(ResponseDtoStatus.FAILURE, "unexpected exception");
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(dto);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value="{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<Void>> deleteVideogame(
			@PathVariable("id") Integer id) {
		ResponseDto<Void> dto = null;
		
		try {
			repository.deleteVideogame(id);
			dto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "videogame deleted");
		} 
		catch (VideogameNotFoundException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "videogame not found");
		}
		catch(Exception e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
		}
		
		return ResponseEntity.ok(dto);
	}
	
}
