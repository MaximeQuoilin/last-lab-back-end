package be.afelio.mqu.gamify.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import be.afelio.mqu.gamify.persistence.entities.VideogameEntity;

public interface VideogameRepository extends JpaRepository<VideogameEntity, Integer> {

	@Query("select v from VideogameEntity v join fetch v.pegis p")
	List<VideogameEntity> fetchAll();
}
