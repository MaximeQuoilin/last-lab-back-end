package be.afelio.mqu.gamify.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.afelio.mqu.gamify.persistence.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	UserEntity findOneById(Integer id);

	UserEntity findOneByEmailIgnoreCase(String email);

	UserEntity findOneByUsernameIgnoreCase(String username);

}
