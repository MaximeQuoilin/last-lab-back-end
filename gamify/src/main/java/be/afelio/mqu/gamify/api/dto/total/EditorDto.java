package be.afelio.mqu.gamify.api.dto.total;

import java.util.Objects;

public class EditorDto {

	private int id;
	private String name;
	public EditorDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public EditorDto() {
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
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EditorDto other = (EditorDto) obj;
		return id == other.id && Objects.equals(name, other.name);
	}
	
}
