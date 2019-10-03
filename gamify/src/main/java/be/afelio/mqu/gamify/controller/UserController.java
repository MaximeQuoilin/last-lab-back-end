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
import be.afelio.mqu.gamify.api.dto.create.AddNewGameToUserDto;
import be.afelio.mqu.gamify.api.dto.create.CreateUserDto;
import be.afelio.mqu.gamify.api.dto.simple.UserSimpleDto;
import be.afelio.mqu.gamify.api.dto.total.UserDto;
import be.afelio.mqu.gamify.api.dto.update.UpdateUserDto;
import be.afelio.mqu.gamify.api.exceptions.UserAlreadyOwnsGameException;
import be.afelio.mqu.gamify.api.exceptions.duplicate.DuplicatedEmailException;
import be.afelio.mqu.gamify.api.exceptions.duplicate.DuplicatedUsernameException;
import be.afelio.mqu.gamify.api.exceptions.notFound.UserNotFoundException;
import be.afelio.mqu.gamify.api.exceptions.notFound.VideogameNotFoundException;
import be.afelio.mqu.gamify.repositories.interfaces.UserApplicationRepositoryInterface;

@Controller
@RequestMapping(value = "user")
public class UserController {
	@Autowired
	UserApplicationRepositoryInterface repository;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "add-game", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<Void>> addGameToUser(@RequestBody AddNewGameToUserDto addNewGameToUserDto) {
		ResponseDto<Void> dto = null;

		try {
			repository.addNewGameToUserDto(addNewGameToUserDto);
			dto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "relation created");
		} catch (UserNotFoundException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "user not found");
		} catch (VideogameNotFoundException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "videogame not found");
		} catch (UserAlreadyOwnsGameException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "User already own that game");
		} catch (Exception e) {
			e.printStackTrace();
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
		}

		return ResponseEntity.ok(dto);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<Void>> createUser(@RequestBody CreateUserDto createUserDto) {
		ResponseDto<Void> dto = null;

		try {
			repository.createUser(createUserDto);
			dto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "user created");
		} catch (DuplicatedUsernameException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "Duplicated username");
		} catch (DuplicatedEmailException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "Duplicated email");
		} catch (Exception e) {
			e.printStackTrace();
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
		}

		return ResponseEntity.ok(dto);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<UserDto>>> findAll() {

		ResponseDto<List<UserDto>> dto = null;
		try {
			List<UserDto> users = repository.findAllUser();
			if (users == null) {
				dto = new ResponseDto<List<UserDto>>(ResponseDtoStatus.FAILURE, " users not found ");
			} else {
				dto = new ResponseDto<List<UserDto>>(ResponseDtoStatus.SUCCESS, users.size() + " users found");
				dto.setPayload(users);
			}
		} catch (Exception e) {
			dto = new ResponseDto<List<UserDto>>(ResponseDtoStatus.FAILURE, "unexpected exception");
			e.printStackTrace();
		}

		return ResponseEntity.ok(dto);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/{id}/owners", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<UserSimpleDto>> findUserForOneVideoGame(
			@PathVariable("id") Integer id) {

		ResponseDto<UserSimpleDto> dto = null;
		try {
			UserSimpleDto user = repository.findUserForOneVideoGame(id);
			if (user == null) {
				dto = new ResponseDto<UserSimpleDto>(ResponseDtoStatus.SUCCESS, "no user for that game");
			}

			else {
				dto = new ResponseDto<UserSimpleDto>(ResponseDtoStatus.SUCCESS, " users found");
			}
			dto.setPayload(user);
		} 
		catch (VideogameNotFoundException e) {
			dto = new ResponseDto<UserSimpleDto>(ResponseDtoStatus.FAILURE, "Videogame not found");
		}
		catch (Exception e) {
			dto = new ResponseDto<UserSimpleDto>(ResponseDtoStatus.FAILURE, "unexpected exception");
			e.printStackTrace();
		}

		return ResponseEntity.ok(dto);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<Void>> updateCustomerEmail(@RequestBody UpdateUserDto updateUserDto) {
		ResponseDto<Void> dto = null;
		try {
			repository.updateUser(updateUserDto);
			dto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "user updated");
		} catch (UserNotFoundException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "user not found (invalid id)");
		} catch (DuplicatedUsernameException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "username not available");
		} catch (DuplicatedEmailException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "Duplicated email");
		} catch (Exception e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
			e.printStackTrace();
		}

		return ResponseEntity.ok(dto);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<Void>> deleteUser(@PathVariable("id") Integer id) {
		ResponseDto<Void> dto = null;

		try {
			repository.deleteUser(id);
			dto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "user deleted");
		} catch (UserNotFoundException e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "user not found");
		} catch (Exception e) {
			dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
		}
		return ResponseEntity.ok(dto);
	}

}
