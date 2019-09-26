package be.afelio.mqu.gamify.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="videogame")
public class VideogameEntity {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="editor")
	private String editor;
	
	@Column(name="genre")
	private String genre;
	
	@Column(name="pegi")
	private String pegi;
	
	@Column(name="platform")
	private String platform;

	public VideogameEntity(int id, String name, String description, String editor, String genre, String pegi,
			String platform) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.editor = editor;
		this.genre = genre;
		this.pegi = pegi;
		this.platform = platform;
	}
	
	public VideogameEntity() {}

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

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPegi() {
		return pegi;
	}

	public void setPegi(String pegi) {
		this.pegi = pegi;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	
	
	
}
