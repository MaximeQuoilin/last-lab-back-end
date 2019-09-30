package be.afelio.mqu.gamify.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.afelio.mqu.gamify.persistence.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	UserEntity findOneByUsername(String username);

}
