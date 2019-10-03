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
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="videogame")
public class VideogameEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="rating")
	private Integer rating;
	
	@ManyToOne
	@JoinColumn(name="editor")
	private EditorEntity editor;
	
	@ManyToOne
	@JoinColumn(name="genre")
	private GenreEntity genre;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="has_pegi",
			joinColumns=@JoinColumn(name="videogame_id"),
			inverseJoinColumns=@JoinColumn(name="pegi_id")
			)
	private List<PegiEntity> pegis;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="existon",
			joinColumns=@JoinColumn(name="videogame_id"),
			inverseJoinColumns=@JoinColumn(name="platform_id")
			)
	private List<PlatformEntity> platforms;
	
	@ManyToOne
	@JoinColumn(name="tuser_id")
	private UserEntity user;
	
	
	public VideogameEntity() {	}

	public VideogameEntity(int id, String name, String description, EditorEntity editor, GenreEntity genre,
			List<PegiEntity> pegis, List<PlatformEntity> platforms) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.editor = editor;
		this.genre = genre;
		this.pegis = pegis;
		this.platforms = platforms;
	}

	

	public VideogameEntity(String name, String description, EditorEntity editor,
			GenreEntity genre, List<PegiEntity> pegis, List<PlatformEntity> platforms) {
		super();
		this.name = name;
		this.description = description;
		this.editor = editor;
		this.genre = genre;
		this.pegis = pegis;
		this.platforms = platforms;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EditorEntity getEditor() {
		return editor;
	}

	public void setEditor(EditorEntity editor) {
		this.editor = editor;
	}

	public GenreEntity getGenre() {
		return genre;
	}

	public void setGenre(GenreEntity genre) {
		this.genre = genre;
	}

	public List<PegiEntity> getPegi() {
		return pegis;
	}

	public void setPegi(List<PegiEntity> pegis) {
		this.pegis = pegis;
	}

	public List<PlatformEntity> getPlatforms() {
		return platforms;
	}

	public void setPlatform(List<PlatformEntity> platforms) {
		this.platforms = platforms;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	
}
