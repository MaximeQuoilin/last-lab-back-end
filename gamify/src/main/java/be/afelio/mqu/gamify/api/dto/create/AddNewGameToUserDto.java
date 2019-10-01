package be.afelio.mqu.gamify.api.dto.create;

import java.util.Objects;

public class AddNewGameToUserDto {
	
	public String nameUser;
	public String nameGame;
	
	public AddNewGameToUserDto(String nameUser, String nameGame) {
		super();
		this.nameUser = nameUser;
		this.nameGame = nameGame;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public String getNameGame() {
		return nameGame;
	}
	public void setNameGame(String nameGame) {
		this.nameGame = nameGame;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nameGame, nameUser);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddNewGameToUserDto other = (AddNewGameToUserDto) obj;
		return Objects.equals(nameGame, other.nameGame) && Objects.equals(nameUser, other.nameUser);
	}
	
	
}
