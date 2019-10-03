package be.afelio.mqu.gamify.persistence.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@Column(name="email")
	private String email;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="owns",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="videogame_id")
			)
	private List<VideogameEntity> videogames;

	public UserEntity(int id, String username, List<VideogameEntity> videogames) {
		this.id = id;
		this.username = username;
		this.videogames = videogames;
	}

	public UserEntity() {
	}

	public UserEntity(String username, String password, String email) {
		super();
		this.username = username;
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
