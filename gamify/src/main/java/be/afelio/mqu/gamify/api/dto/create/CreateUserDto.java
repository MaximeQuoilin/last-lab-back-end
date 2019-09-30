package be.afelio.mqu.gamify.api.dto.create;

import java.util.Objects;

public class CreateUserDto {
	private String username;
	private String password;
	private String email;
	
	public CreateUserDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public CreateUserDto() {
		super();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, password, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateUserDto other = (CreateUserDto) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	
}
