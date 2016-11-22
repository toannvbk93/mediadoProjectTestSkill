package projectTest.example.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 * @authorToanNV
 *
 */
public class CommentTb implements Serializable {

	private int id;

	private int post_id;

	private int user_id;

	private String content;

	private Date createAt;

	private Date updateAt;

	public CommentTb() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setPostId(int post_id) {
		this.post_id = post_id;
	}

	public int getPostId() {
		return post_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public int getUserId() {
		return user_id;
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
