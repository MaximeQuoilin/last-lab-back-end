package be.afelio.mqu.gamify.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.afelio.mqu.gamify.api.exceptions.invalid.InvalidPlatformException;
import be.afelio.mqu.gamify.persistence.entities.PlatformEntity;
import be.afelio.mqu.gamify.persistence.repositories.PlatformRepository;
import be.afelio.mqu.gamify.repositories.interfaces.PlatformApplicationRepositoryInterface;

@Component
public class PlatformApplicationRepository implements PlatformApplicationRepositoryInterface {		
	@Autowired PlatformRepository platformRepository;

	public List<PlatformEntity> createListPlatformEntity(List<String> platforms) {
		List<PlatformEntity> listPlatformEntity = new ArrayList<PlatformEntity>();
		if (platforms != null) {
			for (String platformName : platforms) {
				PlatformEntity platformEntity = platformRepository.findOneByNameIgnoreCase(platformName);
				listPlatformEntity.add(platformEntity);
			}
			if (listPlatformEntity.size() == 0) {
				throw new InvalidPlatformException();
			}
		}
		return listPlatformEntity;
	}
	
}
