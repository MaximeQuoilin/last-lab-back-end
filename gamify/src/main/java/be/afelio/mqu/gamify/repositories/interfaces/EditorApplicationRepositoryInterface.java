package be.afelio.mqu.gamify.repositories.interfaces;

import be.afelio.mqu.gamify.persistence.entities.EditorEntity;

public interface EditorApplicationRepositoryInterface {

	EditorEntity findOneByNameIgnoreCase(String editor);

}
