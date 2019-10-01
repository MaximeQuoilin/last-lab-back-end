package be.afelio.mqu.gamify.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.afelio.mqu.gamify.persistence.entities.PegiEntity;

public interface PegiRepository extends JpaRepository<PegiEntity, Integer> {

	PegiEntity findOneByNameIgnoreCase(String name);

}
