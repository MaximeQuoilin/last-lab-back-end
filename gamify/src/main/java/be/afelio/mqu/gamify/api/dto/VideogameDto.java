package be.afelio.mqu.gamify.api.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import be.afelio.mqu.gamify.persistence.entities.EditorEntity;
import be.afelio.mqu.gamify.persistence.entities.GenreEntity;
import be.afelio.mqu.gamify.persistence.entities.PegiEntity;
import be.afelio.mqu.gamify.persistence.entities.PlatformEntity;

public class VideogameDto {

	private int id;
	private String name;
	private String description;
	private EditorDto editor;
	private GenreDto genre;
	private List<PegiDto> pegis;
	private List<PlatformDto> platforms;
	
	public VideogameDto() {}

	public VideogameDto(int id, String name, String description, EditorEntity editor, GenreEntity genre, List<PegiEntity> pegis,
			List<PlatformEntity> platforms) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.editor = new EditorDto(editor.getId(), editor.getName());
		this.genre = new GenreDto(genre.getId(), genre.getName());
		this.pegis = createLisPegitDto(pegis);
		this.platforms = createListPlatformDto(platforms);
	}

	private List<PlatformDto> createListPlatformDto(List<PlatformEntity> platforms) {
		List<PlatformDto> platformsDto = new ArrayList<PlatformDto>();
		for (PlatformEntity platformEntity : platforms) {
			platformsDto.add(new PlatformDto(platformEntity.getId(), platformEntity.getName()));
		}
		if(platformsDto.size()==0) {
			platformsDto = null;
		}
		return platformsDto;
	}

	private List<PegiDto> createLisPegitDto(List<PegiEntity> pegis) {
		List<PegiDto> pegisDto = new ArrayList<PegiDto>();
		for (PegiEntity pegi : pegis) {
			pegisDto.add(new PegiDto(pegi.getId(), pegi.getName(), pegi.getDescription()));
		}
		if(pegisDto.size()==0) {
			pegisDto = null;
		}
		return pegisDto;
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

	public EditorDto getEditor() {
		return editor;
	}

	public void setEditor(EditorDto editor) {
		this.editor = editor;
	}

	public GenreDto getGenre() {
		return genre;
	}

	public void setGenre(GenreDto genre) {
		this.genre = genre;
	}

	public List<PegiDto> getPegis() {
		return pegis;
	}

	public void setPegis(List<PegiDto> pegis) {
		this.pegis = pegis;
	}

	public List<PlatformDto> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<PlatformDto> platforms) {
		this.platforms = platforms;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, editor, genre, id, name, pegis, platforms);
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
				&& Objects.equals(pegis, other.pegis) && Objects.equals(platforms, other.platforms);
	}
	
}
