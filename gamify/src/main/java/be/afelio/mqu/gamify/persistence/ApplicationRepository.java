package be.afelio.mqu.gamify.persistence;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.afelio.mqu.gamify.api.dto.classic.UserDto;
import be.afelio.mqu.gamify.api.dto.classic.VideogameDto;
import be.afelio.mqu.gamify.api.dto.create.AddNewGameToUserDto;
import be.afelio.mqu.gamify.api.dto.create.CreateUserDto;
import be.afelio.mqu.gamify.api.dto.create.CreateVideogameDto;
import be.afelio.mqu.gamify.api.dto.simple.UserSimpleDto;
import be.afelio.mqu.gamify.api.dto.update.UpdateUserDto;
import be.afelio.mqu.gamify.api.exceptions.DuplicatedEmailException;
import be.afelio.mqu.gamify.api.exceptions.DuplicateVideogameException;
import be.afelio.mqu.gamify.api.exceptions.DuplicatedUsernameException;
import be.afelio.mqu.gamify.api.exceptions.InvalidEditorException;
import be.afelio.mqu.gamify.api.exceptions.InvalidGenreException;
import be.afelio.mqu.gamify.api.exceptions.InvalidParametersException;
import be.afelio.mqu.gamify.api.exceptions.InvalidParametrersException;
import be.afelio.mqu.gamify.api.exceptions.InvalidPegiException;
import be.afelio.mqu.gamify.api.exceptions.InvalidPlatformException;
import be.afelio.mqu.gamify.api.exceptions.UserAlreadyOwnsGameException;
import be.afelio.mqu.gamify.api.exceptions.UserNotFoundException;
import be.afelio.mqu.gamify.api.exceptions.VideogameNotFoundException;
import be.afelio.mqu.gamify.controller.VideogameControllerRepository;
import be.afelio.mqu.gamify.persistence.entities.EditorEntity;
import be.afelio.mqu.gamify.persistence.entities.GenreEntity;
import be.afelio.mqu.gamify.persistence.entities.PegiEntity;
import be.afelio.mqu.gamify.persistence.entities.PlatformEntity;
import be.afelio.mqu.gamify.persistence.entities.UserEntity;
import be.afelio.mqu.gamify.persistence.entities.VideogameEntity;
import be.afelio.mqu.gamify.persistence.repositories.EditorRepository;
import be.afelio.mqu.gamify.persistence.repositories.GenreRepository;
import be.afelio.mqu.gamify.persistence.repositories.PegiRepository;
import be.afelio.mqu.gamify.persistence.repositories.PlatformRepository;
import be.afelio.mqu.gamify.persistence.repositories.UserRepository;
import be.afelio.mqu.gamify.persistence.repositories.VideogameRepository;
import be.afelio.mqu.gamify.utils.DtoBuilder;
import be.afelio.mqu.gamify.utils.EntityBuilder;

@Component
public class ApplicationRepository implements VideogameControllerRepository{
	
	@Autowired VideogameRepository videogameRepository;
	@Autowired UserRepository userRepository;
	@Autowired EditorRepository editorRepository;
	@Autowired GenreRepository genreRepository;
	@Autowired PegiRepository pegiRepository;
	@Autowired PlatformRepository platformRepository;
	
	DtoBuilder dtoBuilder = new DtoBuilder();
	EntityBuilder entityBuilder = new EntityBuilder();

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
		if (userRepository.findOneByUsernameIgnoreCase(username) != null) {
			throw new DuplicatedUsernameException();
		}
		if (userRepository.findOneByEmailIgnoreCase(email) != null) {
			throw new DuplicatedEmailException();
		}
		UserEntity user = new UserEntity(username, password, email);
		
		userRepository.save(user);
	}

	public List<UserSimpleDto> findAllUsersForOneVideoGame(Integer id) {
		
		if (id == null || id < 1) {
			throw new InvalidParameterException();
		}
		
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

	public void createVideogame(CreateVideogameDto createVideogameDto) {
		String name = createVideogameDto.getName();
		String description =createVideogameDto.getDescription();
		EditorEntity editor = editorRepository.findOneByNameIgnoreCase(createVideogameDto.getEditor());
		GenreEntity genre = genreRepository.findOneByNameIgnoreCase(createVideogameDto.getGenre());
		List<PegiEntity> pegis = createListPegisEntity(createVideogameDto.getPegis());
		List<PlatformEntity> platforms = createListPlatformEntity(createVideogameDto.getPlatforms());
		
		
		if(name == null || name.isBlank() || description == null || description.isBlank()) {
			throw new InvalidParametersException();
		}
		if (videogameRepository.findOneByNameIgnoreCase(name) != null) {
			throw new DuplicateVideogameException();
		}
		if (editor == null) {
			throw new InvalidEditorException();
		}
		if (genre == null) {
			throw new InvalidGenreException();
		}
		VideogameEntity videogameEntity = new VideogameEntity(name, description, editor, genre, pegis, platforms);
		videogameRepository.save(videogameEntity);
	}

	private List<PlatformEntity> createListPlatformEntity(List<String> platforms) {
		List<PlatformEntity> listPlatformEntity = new ArrayList<PlatformEntity>();
		if (platforms != null) {
			for (String platformName : platforms) {
				PlatformEntity platformEntity = platformRepository.findOneByNameIgnoreCase(platformName);
				listPlatformEntity.add(platformEntity);
			}
			if (listPlatformEntity.size() == 0) {
				throw new InvalidPlatformException();
			}
		}
		return listPlatformEntity;
	}

	private List<PegiEntity> createListPegisEntity(List<String> pegis) {
		List<PegiEntity> listPegiEntity = new ArrayList<PegiEntity>();
		if (pegis != null) {
			for (String string : pegis) {
				PegiEntity pegiEntity = pegiRepository.findOneByNameIgnoreCase(string);
				listPegiEntity.add(pegiEntity); 
			}
			if (listPegiEntity.size() == 0) {
				throw new InvalidPegiException();
			}
		}
		return listPegiEntity;
	}

	@Override
	public void deleteVideogame(Integer id) {
		VideogameEntity videogame = videogameRepository.findOneById(id);
		if (videogame == null) {
			throw new VideogameNotFoundException();
		}
		videogameRepository.delete(videogame);
	}

	public void deleteUser(Integer id) {
		UserEntity user = userRepository.findOneById(id);
		if (user == null) {
			throw new UserNotFoundException();
		}
		userRepository.delete(user);
	}

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

	public void addNewGameToUserDto(AddNewGameToUserDto addNewGameToUserDto) {
		String nameGame = addNewGameToUserDto.getNameGame();
		String nameUser = addNewGameToUserDto.getNameUser();
		
		if (nameGame != null || nameGame.isBlank() || nameUser != null || nameUser.isBlank()) {
			throw new InvalidParameterException();
		}
		
		VideogameEntity game = videogameRepository.findOneByNameIgnoreCase(nameGame);
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
}
