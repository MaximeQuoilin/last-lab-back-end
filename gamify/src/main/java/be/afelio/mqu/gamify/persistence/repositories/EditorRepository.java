package be.afelio.mqu.gamify.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.afelio.mqu.gamify.persistence.entities.EditorEntity;

public interface EditorRepository extends JpaRepository<EditorEntity, Integer> {

	EditorEntity findOneByNameIgnoreCase(String editor);

}
