package projectTest.example.beans;

import java.util.List;
/**
 * Admin active with post
 *
 * @author ToanNV
 *
 */
public class AdminPost {
	private PostTb post;
	private UserTb user;
	private List<UserComment> listUserComment;

	public AdminPost() {
	}

	public void AdminPost(PostTb post, UserTb user, List<UserComment> listUserComment) {
		this.post = post;
		this.user = user;
		this.listUserComment = listUserComment;
	}

	public PostTb getPost() {
		return post;
	}

	public UserTb getUser() {
		return user;
	}

	public List<UserComment> getListUserComment() {
		return listUserComment;
	}
	public void setListUserComment( List<UserComment> listUserComment){
		this.listUserComment = listUserComment;
	}

}
