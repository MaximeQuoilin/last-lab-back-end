package be.afelio.mqu.gamify.controller;

import java.util.List;

import be.afelio.mqu.gamify.api.dto.classic.VideogameDto;
import be.afelio.mqu.gamify.api.dto.create.CreateVideogameDto;
import be.afelio.mqu.gamify.api.dto.simple.UserSimpleDto;

public interface VideogameControllerRepository {

	void createVideogame(CreateVideogameDto createVideogameDto);

	List<VideogameDto> findAllVideogames();

	List<UserSimpleDto> findAllUsersForOneVideoGame(Integer id);

	VideogameDto findOneVideoGameById(Integer id);

}
