package be.afelio.mqu.gamify.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import be.afelio.mqu.gamify.persistence.entities.VideogameEntity;

public interface VideogameRepository extends JpaRepository<VideogameEntity, Integer> {

	VideogameEntity findOneById(Integer id);
}
