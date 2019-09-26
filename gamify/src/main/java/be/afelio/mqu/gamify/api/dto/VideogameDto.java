package be.afelio.mqu.gamify.api.dto;

import java.util.Objects;

public class VideogameDto {

	private int id;
	private String name;
	private String description;
	private String editor;
	private String genre;
	private String pegi;
	private String platform;
	
	public VideogameDto() {}

	public VideogameDto(int id, String name, String description, String editor, String genre, String pegi,
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

	@Override
	public int hashCode() {
		return Objects.hash(description, editor, genre, id, name, pegi, platform);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VideogameDto other = (VideogameDto) obj;
		return Objects.equals(description, other.description) && Objects.equals(editor, other.editor)
				&& Objects.equals(genre, other.genre) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(pegi, other.pegi) && Objects.equals(platform, other.platform);
	}
	
	
}
