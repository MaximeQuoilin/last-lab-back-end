package be.afelio.mqu.gamify.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.afelio.mqu.gamify.api.dto.VideogameDto;
import be.afelio.mqu.gamify.persistence.entities.VideogameEntity;
import be.afelio.mqu.gamify.persistence.repositories.VideogameRepository;

@Component
public class ApplicationRepository {
	@Autowired VideogameRepository videogameRepository;

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
				videogame.getPlatform());
	}

}
