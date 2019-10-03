package be.afelio.mqu.gamify.api.dto.simple;

import java.util.Objects;

import be.afelio.mqu.gamify.persistence.entities.UserEntity;

public class UserSimpleDto {
	private Integer id;
	private String username;
	private String email;
	
	public UserSimpleDto(Integer id, String username, String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
	}

	public UserSimpleDto(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.username = userEntity.getUsername();
		this.email = userEntity.getEmail();
	}
	
	public UserSimpleDto() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSimpleDto other = (UserSimpleDto) obj;
		return Objects.equals(email, other.email) && Objects.equals(username, other.username);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
