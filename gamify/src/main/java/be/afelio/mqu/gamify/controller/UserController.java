package be.afelio.mqu.gamify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import be.afelio.mqu.gamify.api.dto.ResponseDto;
import be.afelio.mqu.gamify.api.dto.ResponseDtoStatus;
import be.afelio.mqu.gamify.api.dto.classic.UserDto;
import be.afelio.mqu.gamify.api.dto.create.CreateUserDto;
import be.afelio.mqu.gamify.api.exceptions.DuplicatedEmailException;
import be.afelio.mqu.gamify.api.exceptions.DuplicatedUsernameException;
import be.afelio.mqu.gamify.persistence.ApplicationRepository;

@Controller
@RequestMapping(value="user")
public class UserController {
	@Autowired ApplicationRepository repository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="all", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<UserDto>>> findAll() {
		
		ResponseDto<List<UserDto>> dto = null;
		try {
			List<UserDto> users = repository.findAllUser();
			if (users == null) {
				dto = new ResponseDto<List<UserDto>>(ResponseDtoStatus.FAILURE, " users not found ");
			} else {
				dto = new ResponseDto<List<UserDto>>(ResponseDtoStatus.SUCCESS, users.size() +  " users found");
				dto.setPayload(users);
			}
		} 
		catch(Exception e) {
			dto = new ResponseDto<List<UserDto>>(ResponseDtoStatus.FAILURE, "unexpected exception");
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<Void>>  createUser(@RequestBody CreateUserDto createUserDto) {
		ResponseDto<Void> dto = null;
		
		try {
			repository.createUser(createUserDto);
			dto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "user created");
		}
		catch (DuplicatedUsernameException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "Duplicated username");
		}
		catch (DuplicatedEmailException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "Duplicated email");
		}
		catch(Exception e) {
			e.printStackTrace();
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
		}
		
		return ResponseEntity.ok(dto);
	}
}
