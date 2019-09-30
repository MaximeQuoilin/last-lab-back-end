package be.afelio.mqu.gamify.api.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import be.afelio.mqu.gamify.persistence.entities.UserEntity;
import be.afelio.mqu.gamify.persistence.entities.VideogameEntity;

public class UserDto {
	private int id;
	private String username;
	private String password;
	private List<VideogameDto> videogames;
	public UserDto(int id, String username, String password, List<VideogameEntity> videogames) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.videogames = createListVideogamesDto(videogames);
	}
	private List<VideogameDto> createListVideogamesDto(List<VideogameEntity> videogames) {
		List<VideogameDto> videogamesDto = new ArrayList<VideogameDto>();
		for (VideogameEntity videogameEntity : videogames) {
			videogamesDto.add(new VideogameDto(videogameEntity));
		}
		if (videogamesDto.size()==0) {
			videogamesDto=null;
		}
		return videogamesDto;
	}
	public UserDto() {
		super();
	}
	public UserDto(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.username = userEntity.getUsername();
		this.password = userEntity.getPassword();
		this.videogames = createListVideogamesDto(userEntity.getVideogames());
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<VideogameDto> getVideogames() {
		return videogames;
	}
	public void setVideogames(List<VideogameDto> videogames) {
		this.videogames = videogames;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, password, username, videogames);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return id == other.id && Objects.equals(password, other.password) && Objects.equals(username, other.username)
				&& Objects.equals(videogames, other.videogames);
	}
	
}
