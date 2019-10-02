package be.afelio.mqu.gamify.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.afelio.mqu.gamify.persistence.entities.GenreEntity;
import be.afelio.mqu.gamify.persistence.repositories.GenreRepository;

@Component
public class GenreApplicationRepository {	
	@Autowired GenreRepository genreRepository;

	public GenreEntity findOneByNameIgnoreCase(String genre) {
		return genreRepository.findOneByNameIgnoreCase(genre);
	}

}
