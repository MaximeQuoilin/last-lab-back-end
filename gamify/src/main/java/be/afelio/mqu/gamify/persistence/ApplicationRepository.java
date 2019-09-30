package be.afelio.mqu.gamify.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.afelio.mqu.gamify.api.dto.CreateUserDto;
import be.afelio.mqu.gamify.api.dto.UserDto;
import be.afelio.mqu.gamify.api.dto.VideogameDto;
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

	@Transactional
	public List<VideogameDto> findAllVideogames() {
		List<VideogameEntity> videogames = videogameRepository.findAll();
		return createListVideoGameDto(videogames);
	}

	private List<VideogameDto> createListVideoGameDto(List<VideogameEntity> videogames) {
		List<VideogameDto> videogamesDto = new ArrayList<VideogameDto>();
		for (VideogameEntity videogame : videogames) {
			videogamesDto.add(createVideogameDto(videogame));
		}
		if(videogamesDto.size()==0) {
			videogamesDto = null;
		}
		return videogamesDto;
	}

	private VideogameDto createVideogameDto(VideogameEntity videogame) {
		return new VideogameDto(
				videogame.getId(), 
				videogame.getName(), 
				videogame.getDescription(), 
				videogame.getEditor(), 
				videogame.getGenre(), 
				videogame.getPegi(), 
				videogame.getPlatforms());
	}

	public List<UserDto> findAllUser() {
		List<UserEntity> users = userRepository.findAll();
		return createListUsersDto(users);
	}
	
	//Bonne technique ?? ou plutot faire un max en repo ?
	private List<UserDto> createListUsersDto(List<UserEntity> users) {
		List<UserDto> usersDto = new ArrayList<UserDto>();
		for (UserEntity userEntity : users) {
			usersDto.add(new UserDto(userEntity));
		}
		if (usersDto.size()==0) {
			usersDto=null;			
		}
		return usersDto;
	}

	public void createUser(CreateUserDto createUserDto) {
		String username = createUserDto.getUsername();
		String password = createUserDto.getPassword();
		
		if (username == null || username.isBlank() || password == null || password.isBlank()) {
			throw new InvalidParametrersException();
		}
		if (userRepository.findOneByUsername(username) != null) {
			throw new DuplicatedUsernameException();
		}
		UserEntity user = new UserEntity(username, password);
		
		userRepository.save(user);
	}

}
