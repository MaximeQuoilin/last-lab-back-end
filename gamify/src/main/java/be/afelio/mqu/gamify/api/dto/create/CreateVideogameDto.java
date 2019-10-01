package be.afelio.mqu.gamify.api.dto.create;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateVideogameDto {
	
	private String name;
	private String description;
	private String editor;
	private String genre;
	private List<String> pegis;
	private List<String> platforms;
	public CreateVideogameDto(String name, String description, String editor, String genre,
			String[] pegis, String[] platforms) {
		super();
		this.name = name;
		this.description = description;
		this.editor = editor;
		this.genre = genre;
		if (pegis.length > 0) {
			this.pegis = transformTabToList(pegis);
		}
		this.platforms = transformTabToList(platforms);
	}
	private List<String> transformTabToList(String[] tabString) {
		List<String> list = new ArrayList<String>();
		for (String val : tabString) {
			list.add(val);
		}
		if(list.size()==0) {
			list = null;
		}
		return list;
	}
	public CreateVideogameDto() {
		super();
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

	public List<String> getPegis() {
		return pegis;
	}
	public void setPegis(List<String> pegis) {
		this.pegis = pegis;
	}
	public List<String> getPlatforms() {
		return platforms;
	}
	public void setPlatforms(List<String> platforms) {
		this.platforms = platforms;
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, editor, genre, name, pegis, platforms);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateVideogameDto other = (CreateVideogameDto) obj;
		return Objects.equals(description, other.description) && Objects.equals(editor, other.editor)
				&& Objects.equals(genre, other.genre) && Objects.equals(name, other.name)
				&& Objects.equals(pegis, other.pegis) && Objects.equals(platforms, other.platforms);
	}
	

	
}
