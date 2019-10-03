package be.afelio.mqu.gamify.repositories.interfaces;

import java.util.List;

import be.afelio.mqu.gamify.persistence.entities.PlatformEntity;

public interface PlatformApplicationRepositoryInterface {

	List<PlatformEntity> createListPlatformEntity(List<String> platforms);
	
}
