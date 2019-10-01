package be.afelio.mqu.gamify.api.dto.classic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import be.afelio.mqu.gamify.persistence.entities.UserEntity;
import be.afelio.mqu.gamify.persistence.entities.VideogameEntity;

public class UserDto {
	private int id;
	private String username;
	private String email;
	private List<VideogameDto> videogames;
	
	public UserDto(int id, String username, List<VideogameEntity> videogames) {
		super();
		this.id = id;
		this.username = username;
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
	
	public UserDto(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.username = userEntity.getUsername();
		this.setEmail(userEntity.getEmail());
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
	public List<VideogameDto> getVideogames() {
		return videogames;
	}
	public void setVideogames(List<VideogameDto> videogames) {
		this.videogames = videogames;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, username, videogames);
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
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(username, other.username)
				&& Objects.equals(videogames, other.videogames);
	}
	
}
