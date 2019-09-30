package be.afelio.mqu.gamify.persistence.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name="user")
@Table(name="tuser")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@ManyToMany
	@JoinTable(
			name="owns",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="videogame_id")
			)
	private List<VideogameEntity> videogames;

	public UserEntity(int id, String username, String password, List<VideogameEntity> videogames) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.videogames = videogames;
	}

	public UserEntity() {
	}

	public UserEntity(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public List<VideogameEntity> getVideogames() {
		return videogames;
	}

	public void setVideogames(List<VideogameEntity> videogames) {
		this.videogames = videogames;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
