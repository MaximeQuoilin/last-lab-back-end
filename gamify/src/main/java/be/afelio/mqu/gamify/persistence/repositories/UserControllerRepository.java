package be.afelio.mqu.gamify.persistence.repositories;

import java.util.List;

import be.afelio.mqu.gamify.api.dto.classic.UserDto;
import be.afelio.mqu.gamify.api.dto.create.AddNewGameToUserDto;
import be.afelio.mqu.gamify.api.dto.create.CreateUserDto;
import be.afelio.mqu.gamify.api.dto.update.UpdateUserDto;

public interface UserControllerRepository {

	void addNewGameToUserDto(AddNewGameToUserDto addNewGameToUserDto);

	void createUser(CreateUserDto createUserDto);

	void updateUser(UpdateUserDto updateUserDto);

	void deleteUser(Integer id);

	List<UserDto> findAllUser();

}
