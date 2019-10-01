package be.afelio.mqu.gamify.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.afelio.mqu.gamify.persistence.entities.GenreEntity;

public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {

	GenreEntity findOneByNameIgnoreCase(String genre);

}
