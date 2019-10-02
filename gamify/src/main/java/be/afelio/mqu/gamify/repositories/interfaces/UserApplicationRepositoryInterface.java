package be.afelio.mqu.gamify.repositories.interfaces;

import java.util.List;

import be.afelio.mqu.gamify.api.dto.create.AddNewGameToUserDto;
import be.afelio.mqu.gamify.api.dto.create.CreateUserDto;
import be.afelio.mqu.gamify.api.dto.simple.UserSimpleDto;
import be.afelio.mqu.gamify.api.dto.total.UserDto;
import be.afelio.mqu.gamify.api.dto.update.UpdateUserDto;

public interface UserApplicationRepositoryInterface {

	void addNewGameToUserDto(AddNewGameToUserDto addNewGameToUserDto);

	List<UserDto> findAllUser();
	
	List<UserSimpleDto> findAllUsersForOneVideoGame(Integer id);
	
	void createUser(CreateUserDto createUserDto);

	void updateUser(UpdateUserDto updateUserDto);

	void deleteUser(Integer id);

}
