package be.afelio.mqu.gamify.repositories.interfaces;

import be.afelio.mqu.gamify.persistence.entities.GenreEntity;

public interface GenreApplicationRepositoryInterface {

	GenreEntity findOneByNameIgnoreCase(String genre);

}
