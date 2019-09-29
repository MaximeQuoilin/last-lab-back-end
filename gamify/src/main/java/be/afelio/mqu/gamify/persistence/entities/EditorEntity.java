package be.afelio.mqu.gamify.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="editor")
public class EditorEntity {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	public EditorEntity() {}

	public EditorEntity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	
}
