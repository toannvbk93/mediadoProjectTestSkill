package projectTest.example.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ToanNV
 *
 */
public class UserTb implements Serializable {

	private int id;
	private String username;
	private String email;
	private String password;
	private int role;
	private Boolean active;
	private Date createAt;
	private Date updateAt;

	public UserTb() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getRole() {
		return this.role;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Date getUpdateAt() {
		return this.updateAt;
	}
}
