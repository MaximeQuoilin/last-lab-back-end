package be.afelio.mqu.gamify.api.dto.classic;

import java.util.Objects;

public class PegiDto {

	private int id;
	private String name;
	private String description;
	public PegiDto(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public PegiDto() {
		super();
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
	@Override
	public int hashCode() {
		return Objects.hash(description, id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PegiDto other = (PegiDto) obj;
		return Objects.equals(description, other.description) && id == other.id && Objects.equals(name, other.name);
	}
	
	
}
