package projectTest.example.beans;

import java.io.Serializable;

/**
 *
 *
 * @author ToanNV
 *
 */
public class TagTb implements Serializable {

	private int id;

	private String name;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
