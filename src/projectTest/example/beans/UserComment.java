package projectTest.example.beans;

public class UserComment {
	private UserTb user;
	private CommentTb comment;

	public UserComment() {
	}

	public void setUser(UserTb user) {
		this.user = user;
	}

	public void setComment(CommentTb comment) {
		this.comment = comment;
	}

	public UserTb getUser() {
		return user;
	}

	public CommentTb getComment() {
		return comment;
	}
}
