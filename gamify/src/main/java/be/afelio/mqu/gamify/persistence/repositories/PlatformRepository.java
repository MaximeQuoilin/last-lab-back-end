package be.afelio.mqu.gamify.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.afelio.mqu.gamify.persistence.entities.PlatformEntity;

public interface PlatformRepository extends JpaRepository<PlatformEntity, Integer> {

	PlatformEntity findOneByNameIgnoreCase(String platformName);

}
