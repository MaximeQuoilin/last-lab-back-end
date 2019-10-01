package be.afelio.mqu.gamify.utils;

import java.util.ArrayList;
import java.util.List;

import be.afelio.mqu.gamify.api.dto.classic.UserDto;
import be.afelio.mqu.gamify.api.dto.classic.VideogameDto;
import be.afelio.mqu.gamify.api.dto.simple.UserSimpleDto;
import be.afelio.mqu.gamify.persistence.entities.UserEntity;
import be.afelio.mqu.gamify.persistence.entities.VideogameEntity;

public class DtoBuilder {
	public List<VideogameDto> createListVideoGameDto(List<VideogameEntity> videogames) {
		List<VideogameDto> videogamesDto = new ArrayList<VideogameDto>();
		for (VideogameEntity videogame : videogames) {
			videogamesDto.add(createVideogameDto(videogame));
		}
		if (videogamesDto.size() == 0) {
			videogamesDto = null;
		}
		return videogamesDto;
	}

	private VideogameDto createVideogameDto(VideogameEntity videogame) {
		return new VideogameDto(videogame.getId(), videogame.getName(), videogame.getDescription(),
				videogame.getRating(), videogame.getEditor(), videogame.getGenre(), videogame.getPegi(),
				videogame.getPlatforms());
	}

	// Bonne technique ?? ou plutot faire un max en repo ?
	public List<UserDto> createListUsersDto(List<UserEntity> users) {
		List<UserDto> usersDto = new ArrayList<UserDto>();
		for (UserEntity userEntity : users) {
			usersDto.add(new UserDto(userEntity));
		}
		if (usersDto.size() == 0) {
			usersDto = null;
		}
		return usersDto;
	}

	public List<UserSimpleDto> createListUsersSimpleDto(List<UserEntity> users) {
		List<UserSimpleDto> usersSimpleDto = new ArrayList<UserSimpleDto>();
		for (UserEntity userEntity : users) {
			usersSimpleDto.add(new UserSimpleDto(userEntity));
		}
		if (usersSimpleDto.size() == 0) {
			usersSimpleDto = null;
		}
		return usersSimpleDto;
	}
}
