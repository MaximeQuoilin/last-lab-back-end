package be.afelio.mqu.gamify.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.afelio.mqu.gamify.api.dto.create.CreateVideogameDto;
import be.afelio.mqu.gamify.api.dto.total.VideogameDto;
import be.afelio.mqu.gamify.api.exceptions.duplicate.DuplicateVideogameException;
import be.afelio.mqu.gamify.api.exceptions.invalid.InvalidEditorException;
import be.afelio.mqu.gamify.api.exceptions.invalid.InvalidGenreException;
import be.afelio.mqu.gamify.api.exceptions.invalid.InvalidParametersException;
import be.afelio.mqu.gamify.api.exceptions.notFound.VideogameNotFoundException;
import be.afelio.mqu.gamify.persistence.entities.EditorEntity;
import be.afelio.mqu.gamify.persistence.entities.GenreEntity;
import be.afelio.mqu.gamify.persistence.entities.PegiEntity;
import be.afelio.mqu.gamify.persistence.entities.PlatformEntity;
import be.afelio.mqu.gamify.persistence.entities.VideogameEntity;
import be.afelio.mqu.gamify.persistence.repositories.VideogameRepository;
import be.afelio.mqu.gamify.repositories.interfaces.EditorApplicationRepositoryInterface;
import be.afelio.mqu.gamify.repositories.interfaces.GenreApplicationRepositoryInterface;
import be.afelio.mqu.gamify.repositories.interfaces.PegiApplicationRepositoryInterface;
import be.afelio.mqu.gamify.repositories.interfaces.PlatformApplicationRepositoryInterface;
import be.afelio.mqu.gamify.repositories.interfaces.VideogameApplicationRepositoryInterface;
import be.afelio.mqu.gamify.utils.DtoBuilder;
import be.afelio.mqu.gamify.utils.EntityBuilder;

@Component
public class VideogameApplicationRepository implements VideogameApplicationRepositoryInterface {

	@Autowired VideogameRepository videogameRepository;
	
	@Autowired PegiApplicationRepositoryInterface pegiApplicationRepository;
	@Autowired PlatformApplicationRepositoryInterface  platformApplicationRepository; 
	@Autowired GenreApplicationRepositoryInterface genreControllerRepository;
	@Autowired EditorApplicationRepositoryInterface editorApplicationRepository;
	
	DtoBuilder dtoBuilder = new DtoBuilder();
	EntityBuilder entityBuilder = new EntityBuilder();
	
	@Override
	public void createVideogame(CreateVideogameDto createVideogameDto) {
		String name = createVideogameDto.getName();
		String description =createVideogameDto.getDescription();
		EditorEntity editor = editorApplicationRepository.findOneByNameIgnoreCase(createVideogameDto.getEditor());
		GenreEntity genre = genreControllerRepository.findOneByNameIgnoreCase(createVideogameDto.getGenre());
		List<PegiEntity> pegis = pegiApplicationRepository.createListPegisEntity(createVideogameDto.getPegis());
		List<PlatformEntity> platforms = platformApplicationRepository.createListPlatformEntity(createVideogameDto.getPlatforms());
		
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

	@Override
	public List<VideogameDto> findAllVideogames() {
		List<VideogameEntity> videogames = videogameRepository.findAll();
		return dtoBuilder.createListVideoGameDto(videogames);
	}

	@Override
	public VideogameDto findOneVideoGameById(Integer id) {
		VideogameDto videogameDto = null;
		VideogameEntity videogame = videogameRepository.findOneById(id);
		if (videogame != null) {
			videogameDto = new VideogameDto(videogame);
		}
		return videogameDto;
	}

	@Override
	public void deleteVideogame(Integer id) {
		VideogameEntity videogame = videogameRepository.findOneById(id);
		if (videogame == null) {
			throw new VideogameNotFoundException();
		}
		videogameRepository.delete(videogame);
	}

	@Override
	public VideogameEntity findOneByNameIgnoreCase(String nameGame) {
		return videogameRepository.findOneByNameIgnoreCase(nameGame);
	}

	@Override
	public VideogameEntity findOneById(Integer id) {
		// TODO Better to throw exception here ? 
		return videogameRepository.findOneById(id);
	}

}
