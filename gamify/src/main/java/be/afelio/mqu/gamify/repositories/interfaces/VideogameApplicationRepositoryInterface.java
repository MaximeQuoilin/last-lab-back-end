package be.afelio.mqu.gamify.repositories.interfaces;

import java.util.List;

import be.afelio.mqu.gamify.api.dto.create.CreateVideogameDto;
import be.afelio.mqu.gamify.api.dto.total.VideogameDto;
import be.afelio.mqu.gamify.persistence.entities.VideogameEntity;

public interface VideogameApplicationRepositoryInterface {

	void createVideogame(CreateVideogameDto createVideogameDto);

	List<VideogameDto> findAllVideogames();

	VideogameDto findOneVideoGameById(Integer id);

	void deleteVideogame(Integer id);

	VideogameEntity findOneByNameIgnoreCase(String nameGame);

	VideogameEntity findOneById(Integer id);

}
