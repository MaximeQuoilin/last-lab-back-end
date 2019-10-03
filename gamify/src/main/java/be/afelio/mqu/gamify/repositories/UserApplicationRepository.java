package be.afelio.mqu.gamify.repositories;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
import be.afelio.mqu.gamify.persistence.entities.UserEntity;
import be.afelio.mqu.gamify.persistence.entities.VideogameEntity;
import be.afelio.mqu.gamify.persistence.repositories.UserRepository;
import be.afelio.mqu.gamify.repositories.interfaces.UserApplicationRepositoryInterface;
import be.afelio.mqu.gamify.repositories.interfaces.VideogameApplicationRepositoryInterface;
import be.afelio.mqu.gamify.utils.DtoBuilder;

@Component
public class UserApplicationRepository implements UserApplicationRepositoryInterface {

	@Autowired
	UserRepository userRepository;

	@Autowired
	VideogameApplicationRepositoryInterface videogameApplicationRepository;

	private DtoBuilder dtoBuilder = new DtoBuilder();

	@Override
	public void addNewGameToUserDto(AddNewGameToUserDto addNewGameToUserDto) {
		String nameGame = addNewGameToUserDto.getNameGame();
		String nameUser = addNewGameToUserDto.getNameUser();

		if (nameGame == null || nameGame.isBlank() || nameUser == null || nameUser.isBlank()) {
			throw new InvalidParameterException();
		}

		VideogameEntity game = videogameApplicationRepository.findOneByNameIgnoreCase(nameGame);
		if (game == null) {
			throw new VideogameNotFoundException();
		}

		UserEntity user = userRepository.findOneByUsernameIgnoreCase(nameUser);
		if (user == null) {
			throw new UserNotFoundException();
		}

		if (user.getVideogames().contains(game)) {
			throw new UserAlreadyOwnsGameException();
		}

		user.getVideogames().add(game);
		userRepository.save(user);

	}

	@Override
	public List<UserDto> findAllUser() {
		List<UserEntity> users = userRepository.findAll();
		return dtoBuilder.createListUsersDto(users);
	}
	
	@Transactional
	@Override
	public UserSimpleDto findUserForOneVideoGame(Integer id) {

		if (id == null || id < 1) {
			throw new InvalidParameterException();
		}

		VideogameEntity videogame = videogameApplicationRepository.findOneById(id);
		if (videogame == null) {
			throw new VideogameNotFoundException();
		}
		
		UserSimpleDto userSimpleDto = null;
		UserEntity userEntity = videogame.getUser();
		if (userEntity != null) {
		    userSimpleDto = new UserSimpleDto(userEntity);
		}
		return userSimpleDto;
	}

	@Override
	public void createUser(CreateUserDto createUserDto) {
		String username = createUserDto.getUsername();
		String email = createUserDto.getEmail();
		
		if (username == null || username.isBlank() 
				|| email == null || email.isBlank()) {
			throw new InvalidParameterException();
		}
		if (userRepository.findOneByUsernameIgnoreCase(username) != null) {
			throw new DuplicatedUsernameException();
		}
		if (userRepository.findOneByEmailIgnoreCase(email) != null) {
			throw new DuplicatedEmailException();
		}
		UserEntity user = new UserEntity(username, null, email);
		
		userRepository.save(user);
	}

	@Override
	public void updateUser(UpdateUserDto updateUserDto) {

		Integer id = updateUserDto.getId();
		String username = updateUserDto.getUsername();
		String email = updateUserDto.getEmail();

		if (id == null || id < 1 || username == null || username.isBlank() || email == null || email.isBlank()) {
			throw new InvalidParameterException();
		}
		UserEntity user = userRepository.findOneById(id);
		if (user == null) {
			throw new UserNotFoundException();
		}
		UserEntity tempUser = userRepository.findOneByUsernameIgnoreCase(username);
		if (tempUser != user && tempUser != null) {
			throw new DuplicatedUsernameException();
		}
		tempUser = userRepository.findOneByEmailIgnoreCase(email);
		System.out.println("temp user : " + tempUser);
		System.out.println("user : " + user);
		if (tempUser != user && tempUser != null) {
			throw new DuplicatedEmailException();
		}

		user.setUsername(username);
		user.setEmail(email);
		userRepository.save(user);

	}

	@Override
	public void deleteUser(Integer id) {
		UserEntity user = userRepository.findOneById(id);
		if (user == null) {
			throw new UserNotFoundException();
		}
		if(!user.getVideogames().isEmpty()) {
			//throw new UnbreackableLinkException();
		}
		userRepository.delete(user);
	}

}
