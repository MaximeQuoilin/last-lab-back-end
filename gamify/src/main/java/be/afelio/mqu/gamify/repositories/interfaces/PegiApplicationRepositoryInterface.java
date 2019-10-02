package be.afelio.mqu.gamify.repositories.interfaces;

import java.util.List;

import be.afelio.mqu.gamify.persistence.entities.PegiEntity;

public interface PegiApplicationRepositoryInterface {

	List<PegiEntity> createListPegisEntity(List<String> pegis);

}
