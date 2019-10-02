package be.afelio.mqu.gamify.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.afelio.mqu.gamify.api.exceptions.invalid.InvalidPegiException;
import be.afelio.mqu.gamify.persistence.entities.PegiEntity;
import be.afelio.mqu.gamify.persistence.repositories.PegiRepository;
import be.afelio.mqu.gamify.repositories.interfaces.PegiApplicationRepositoryInterface;

@Component
public class PegiApplicationRepository implements PegiApplicationRepositoryInterface {
	@Autowired PegiRepository pegiRepository;
	
	public List<PegiEntity> createListPegisEntity(List<String> pegis) {
		List<PegiEntity> listPegiEntity = new ArrayList<PegiEntity>();
		if (pegis != null) {
			for (String string : pegis) {
				PegiEntity pegiEntity = pegiRepository.findOneByNameIgnoreCase(string);
				listPegiEntity.add(pegiEntity); 
			}
			if (listPegiEntity.size() == 0) {
				throw new InvalidPegiException();
			}
		}
		return listPegiEntity;
	}
}
