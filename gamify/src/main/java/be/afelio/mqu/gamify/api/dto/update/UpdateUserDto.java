package be.afelio.mqu.gamify.api.dto.update;

import java.util.Objects;

public class UpdateUserDto {
	private int id;
	private String username;
	private String email;
	public UpdateUserDto(int id, String username, String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, id, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateUserDto other = (UpdateUserDto) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(username, other.username);
	}

}
