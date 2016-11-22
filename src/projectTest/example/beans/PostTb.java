package projectTest.example.beans;

import java.io.Serializable;
import java.util.Date;
/**
 * Active with database
 *
 * @author ToanNV
 *
 */
public class PostTb implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private int userId;
	private String content;
	private Date createAt;
	private Date updateAt;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
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
