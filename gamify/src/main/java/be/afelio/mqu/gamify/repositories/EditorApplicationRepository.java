package be.afelio.mqu.gamify.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.afelio.mqu.gamify.persistence.entities.EditorEntity;
import be.afelio.mqu.gamify.persistence.repositories.EditorRepository;
import be.afelio.mqu.gamify.repositories.interfaces.EditorApplicationRepositoryInterface;

@Component
public class EditorApplicationRepository implements EditorApplicationRepositoryInterface {
	@Autowired EditorRepository editorRepository;

	public EditorEntity findOneByNameIgnoreCase(String editor) {
		return editorRepository.findOneByNameIgnoreCase(editor);
	}

}
