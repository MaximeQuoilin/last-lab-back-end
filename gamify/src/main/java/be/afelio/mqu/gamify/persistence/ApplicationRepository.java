package be.afelio.mqu.gamify.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.afelio.mqu.gamify.api.dto.classic.UserDto;
import be.afelio.mqu.gamify.api.dto.classic.VideogameDto;
import be.afelio.mqu.gamify.api.dto.create.CreateUserDto;
import be.afelio.mqu.gamify.api.dto.dtoBuilder.DtoBuilder;
import be.afelio.mqu.gamify.api.dto.simple.UserSimpleDto;
import be.afelio.mqu.gamify.api.exceptions.DuplicatedUsernameException;
import be.afelio.mqu.gamify.api.exceptions.InvalidParametrersException;
import be.afelio.mqu.gamify.persistence.entities.UserEntity;
import be.afelio.mqu.gamify.persistence.entities.VideogameEntity;
import be.afelio.mqu.gamify.persistence.repositories.UserRepository;
import be.afelio.mqu.gamify.persistence.repositories.VideogameRepository;

@Component
public class ApplicationRepository {
	
	@Autowired VideogameRepository videogameRepository;
	@Autowired UserRepository userRepository;
	
	DtoBuilder dtoBuilder = new DtoBuilder();

	@Transactional
	public List<VideogameDto> findAllVideogames() {
		List<VideogameEntity> videogames = videogameRepository.findAll();
		return dtoBuilder.createListVideoGameDto(videogames);
	}
	
	public List<UserDto> findAllUser() {
		List<UserEntity> users = userRepository.findAll();
		return dtoBuilder.createListUsersDto(users);
	}

	public void createUser(CreateUserDto createUserDto) {
		String username = createUserDto.getUsername();
		String password = createUserDto.getPassword();
		String email = createUserDto.getEmail();
		
		if (username == null || username.isBlank() || password == null || password.isBlank() 
				|| email == null || email.isBlank()) {
			throw new InvalidParametrersException();
		}
		if (userRepository.findOneByUsername(username) != null) {
			throw new DuplicatedUsernameException();
		}
		UserEntity user = new UserEntity(username, password, email);
		
		userRepository.save(user);
	}

	public List<UserSimpleDto> findAllUsersForOneVideoGame(Integer id) {
		VideogameEntity videogame = videogameRepository.findOneById(id);
		List<UserSimpleDto> usersSimpleDto = null;
		if (videogame != null) {
			usersSimpleDto = dtoBuilder.createListUsersSimpleDto(videogame.getUsers());
		}
		 
		return usersSimpleDto;
	}

	public VideogameDto findOneVideoGameById(Integer id) {
		VideogameDto videogameDto = null;
		VideogameEntity videogame = videogameRepository.findOneById(id);
		if (videogame != null) {
			videogameDto = new VideogameDto(videogame);
		}
		return videogameDto;
	}

}
