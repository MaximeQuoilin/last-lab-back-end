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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import be.afelio.mqu.gamify.api.dto.ResponseDto;
import be.afelio.mqu.gamify.api.dto.ResponseDtoStatus;
import be.afelio.mqu.gamify.api.dto.classic.UserDto;
import be.afelio.mqu.gamify.api.dto.create.CreateUserDto;
import be.afelio.mqu.gamify.api.exceptions.DuplicatedEmailException;
import be.afelio.mqu.gamify.api.exceptions.DuplicatedUsernameException;
import be.afelio.mqu.gamify.api.exceptions.UserNotFoundException;
import be.afelio.mqu.gamify.persistence.ApplicationRepository;
import be.afelio.software_academy.spring_mvc.example.dvdrental.dto.UpdateCustomerEmailDto;
import be.afelio.software_academy.spring_mvc.example.dvdrental.persistence.exceptions.CustomerNotFoundException;

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
	
	@DeleteMapping(value="{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<Void>> deleteUser(
			@PathVariable("id") Integer id) {
		ResponseDto<Void> dto = null;
		
		try {
			repository.deleteUser(id);
			dto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "user deleted");
		}
		catch(UserNotFoundException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "user not found");
		}
		catch(Exception e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
		}
		
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<Void>> updateCustomerEmail(@RequestBody UpdateCustomerEmailDto updateCustomerEmailDto) {
		ResponseDto<Void> dto = null;		
		try {
			repository.updateCustomerEmail(updateCustomerEmailDto.getFirstname(), 
					updateCustomerEmailDto.getName(), updateCustomerEmailDto.getEmail());
			dto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "email updated");
		}
		catch(Exception e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(dto);
	}
	
}
