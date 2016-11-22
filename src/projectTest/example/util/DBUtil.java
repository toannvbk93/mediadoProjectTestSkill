package projectTest.example.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import projectTest.example.beans.CommentTb;
import projectTest.example.beans.PostTb;
import projectTest.example.beans.TagTb;
import projectTest.example.beans.UserComment;
import projectTest.example.beans.UserTb;

/**
 * Active with database
 *
 * @author ToanNV
 *
 */
public class DBUtil {
	// Find user with username and password
	private int noOfRecords;

	public DBUtil() {
	}

	public UserTb findUser(Connection conn, String email, String password) throws SQLException, ParseException {
		String sql = "Select a.id, a.email, a.password, a.username, a.create_at from user_tb a "
				+ " where a.email = ? and a.password= ? and active=true";
		PreparedStatement pstm = conn.prepareStatement(sql);
		Encode encode = new Encode();
		String password_ = encode.getMD5(password.trim());
		pstm.setString(1, email);
		pstm.setString(2, password_);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			UserTb user = new UserTb();
			user.setEmail(email);
			user.setPassword(password);
			user.setUsername(rs.getString("username"));
			user.setCreateAt(rs.getDate("create_at"));
			user.setId(rs.getInt("id"));
			return user;
		}
		return null;
	}

	// find user with email
	public UserTb findUser(Connection conn, String email) throws SQLException {
		String sql = "Select a.email from user_tb a " + " where a.email = ? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, email);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			UserTb user = new UserTb();
			user.setEmail(email);
			return user;
		}
		return null;
	}

	// find user by Id
	public UserTb findUserById(Connection conn, int id) throws SQLException {
		String sql = "Select * from user_tb a " + " where a.id = ? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			UserTb user = new UserTb();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setCreateAt(rs.getDate("create_at"));
			return user;
		}
		return null;
	}

	// insert new user, no active
	public void insertUser(Connection conn, String username, String password, String email) throws SQLException {
		Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		String sql = "INSERT INTO user_tb(username, email, password, create_at) VALUES (?, ?, ?,?);";
		Encode encode = new Encode();
		UserTb user = null;
		user = findUser(conn, email);
		if (user != null)
			return;
		password = encode.getMD5(password);
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		pstm.setString(2, email);
		pstm.setString(3, password);
		pstm.setTimestamp(4, date);
		System.out.println(pstm);
		try {
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// active account
	public boolean activeAccount(Connection conn, String email, String password) throws SQLException {
		String sql = "UPDATE user_tb SET active = true WHERE email = ? AND password = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		Encode encode = new Encode();
		password = encode.getMD5(password);
		pstm.setString(1, email);
		pstm.setString(2, password);
		System.out.println(pstm);
		try {
			pstm.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Update account
	public boolean UpdateAccount(Connection conn, String username, int id, String password) throws SQLException {
		Encode encode = new Encode();
		password = encode.getMD5(encode.getMD5(password));
		java.sql.Date today = java.sql.Date.valueOf(LocalDate.now());
		String sql = "UPDATE user_tb SET username=?, password=? , update_at=? WHERE id = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		pstm.setString(2, password);
		pstm.setDate(3, today);
		pstm.setInt(4, id);
		System.out.println(pstm);
		try {
			pstm.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// reset password
	public boolean resetPassword(Connection conn, String email, String password) throws SQLException {
		Encode encode = new Encode();
		password = encode.getMD5(password.trim());
		java.sql.Date today = java.sql.Date.valueOf(LocalDate.now());
		String sql = "UPDATE user_tb SET password=? , update_at=? WHERE email = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, password);
		pstm.setDate(2, today);
		pstm.setString(3, email);
		System.out.println(pstm);
		try {
			pstm.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// List all post
	public List<PostTb> listPost(Connection conn, int offset) throws SQLException {
		String sql = "Select * from  public.post_tb order by id desc limit 5 OFFSET " + offset;
		List<PostTb> listPost = new ArrayList<PostTb>();
		PreparedStatement pstm = conn.prepareStatement(sql);
		System.out.println(pstm);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			PostTb post = new PostTb();
			post.setId(rs.getInt("id"));
			post.setTitle(rs.getString("title"));
			post.setContent(rs.getString("content"));
			post.setCreateAt(rs.getDate("create_at"));
			post.setUpdateAt(rs.getDate("update_at"));
			post.setUserId(rs.getInt("user_id"));
			listPost.add(post);
		}
		PreparedStatement pstm_count = conn.prepareStatement("SELECT count(*) from post_tb");
		ResultSet rs_ = pstm_count.executeQuery();
		if (rs_.next()) {
			this.noOfRecords = rs_.getInt(1);
		}
		if (listPost != null) {
			return listPost;
		}
		return null;
	}

	// get noOfRecords;
	public int getNoOfRecords() {
		return noOfRecords;
	}

	// find post by id
	public PostTb postDetail(Connection conn, int id) throws SQLException {
		String sql = "Select * from  public.post_tb where post_tb.id=? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		PostTb post = new PostTb();
		while (rs.next()) {
			post.setId(rs.getInt("id"));
			post.setTitle(rs.getString("title"));
			post.setContent(rs.getString("content"));
			post.setCreateAt(rs.getDate("create_at"));
			post.setUpdateAt(rs.getDate("update_at"));
			post.setUserId(rs.getInt("user_id"));
		}
		if (post != null) {
			return post;
		}
		return null;
	}

	// find all tag by post id
	public List<TagTb> findTagByPostId(Connection conn, int postId) throws SQLException {
		String sql = "select distinct * from tag_tb, (select post_tag_tb.post_id, post_tag_tb.tag_id from post_tag_tb, post_tb where post_tag_tb.post_id =?) post_tag where tag_tb.id=post_tag.tag_id ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, postId);
		System.out.println(pstm);
		List<TagTb> listTag = new ArrayList<TagTb>();
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			TagTb tag = new TagTb();
			tag.setId(rs.getInt("id"));
			tag.setName(rs.getString("name"));
			listTag.add(tag);
		}
		return listTag;
	}

	// Count comment in post by post id
	public int CountCommentByPostId(Connection conn, int postId) throws SQLException {
		String sql = "select count(*) from comment_tb where post_id = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, postId);
		System.out.println(pstm);
		ResultSet rs = pstm.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("count");
		}
		return count;
	}

	// find all comment by post id
	public List<UserComment> findCommentByPostId(Connection conn, int postId, List<UserComment> listUserComment)
			throws SQLException {
		String sql = "select * from comment_tb where post_id = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, postId);
		System.out.println(pstm);
		ResultSet rs = pstm.executeQuery();
		UserTb user = new UserTb();
		while (rs.next()) {
			UserComment userComment = new UserComment();
			CommentTb comment = new CommentTb();
			comment.setId(rs.getInt("id"));
			comment.setPostId(rs.getInt("post_id"));
			comment.setUserId(rs.getInt("user_id"));
			comment.setContent(rs.getString("content"));
			comment.setCreateAt(rs.getDate("create_at"));
			user = findUserById(conn, comment.getUserId());
			userComment.setUser(user);
			userComment.setComment(comment);
			listUserComment.add(userComment);
		}
		return listUserComment;
	}

	// show all post of user
	public List<PostTb> findMyPost(Connection conn, int userId) throws SQLException {
		String sql = "select * from post_tb where user_id = ?";
		List<PostTb> listPost = new ArrayList<PostTb>();
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, userId);
		System.out.println(pstm);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			PostTb post = new PostTb();
			post.setId(rs.getInt("id"));
			post.setContent(rs.getString("content"));
			post.setTitle(rs.getString("title"));
			post.setUserId(rs.getInt("user_id"));
			post.setCreateAt(rs.getDate("create_at"));
			post.setUpdateAt(rs.getDate("update_at"));
			listPost.add(post);
		}
		return listPost;
	}

	// find post like title
	public List<PostTb> findPostByTitle(Connection conn, String title) throws SQLException {
		String sql = "select * from post_tb where lower(post_tb.title) like ?";
		List<PostTb> listPost = new ArrayList<PostTb>();
		PreparedStatement pstm = conn.prepareStatement(sql);
		title = title.trim().toLowerCase();
		pstm.setString(1, "%" + title + "%");
		System.out.println(pstm);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			PostTb post = new PostTb();
			post.setId(rs.getInt("id"));
			post.setContent(rs.getString("content"));
			post.setTitle(rs.getString("title"));
			post.setUserId(rs.getInt("user_id"));
			post.setCreateAt(rs.getDate("create_at"));
			post.setUpdateAt(rs.getDate("update_at"));
			listPost.add(post);
		}
		return listPost;
	}

	// find post like tag
	public List<PostTb> findPostByTag(Connection conn, String tag) throws SQLException {
		String sql = "select post_tb.id, post_tb.title, post_tb.user_id, post_tb.content, post_tb.create_at from post_tb, (select post_tag_tb.post_id from (select * from tag_tb where lower(tag_tb.name) like ?) sql1, post_tag_tb where sql1.id = post_tag_tb.tag_id) sql2 where post_tb.id = sql2.post_id";
		List<PostTb> listPost = new ArrayList<PostTb>();
		PreparedStatement pstm = conn.prepareStatement(sql);
		tag = tag.trim().toLowerCase();
		pstm.setString(1, "%" + tag + "%");
		System.out.println(pstm);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			PostTb post = new PostTb();
			post.setId(rs.getInt("id"));
			post.setContent(rs.getString("content"));
			post.setTitle(rs.getString("title"));
			post.setUserId(rs.getInt("user_id"));
			post.setCreateAt(rs.getDate("create_at"));
			listPost.add(post);
		}
		return listPost;
	}

	// show all tag
	public List<TagTb> showAllTag(Connection conn) throws SQLException {
		String sql = "select * from tag_tb";
		List<TagTb> listTag = new ArrayList<TagTb>();
		PreparedStatement pstm = conn.prepareStatement(sql);
		System.out.println(pstm);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			TagTb tag = new TagTb();
			tag.setId(rs.getInt("id"));
			tag.setName(rs.getString("name"));
			listTag.add(tag);
		}
		return listTag;
	}

	// Add post
	public boolean addPost(Connection conn, String title, String content, int user_id) throws SQLException {
		String sql = "INSERT INTO public.post_tb(title, user_id, content, create_at) VALUES (?, ?, ?, ?);";
		PreparedStatement pstm = conn.prepareStatement(sql);
		Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		pstm.setString(1, title);
		pstm.setInt(2, user_id);
		pstm.setString(3, content);
		pstm.setTimestamp(4, date);
		System.out.println(pstm);
		try {
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// add tag
	public boolean addTag(Connection conn, String name) throws SQLException {
		String sql = "INSERT INTO tag_tb(name) VALUES (?);";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, name);
		System.out.println(pstm);
		try {
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// add relation post and tag
	public boolean addRelationPostTag(Connection conn, int post_id, int tag_id) throws SQLException {
		String sql = "INSERT INTO public.post_tag_tb(post_id, tag_id) VALUES (?, ?);";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, post_id);
		pstm.setInt(2, tag_id);
		try {
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// return last id of post
	public int lastPost(Connection conn) throws SQLException {
		String sql = "SELECT id from post_tb order by id desc limit 1";
		PreparedStatement pstm = conn.prepareStatement(sql);
		int lastPostId = 0;
		try {
			ResultSet rs = pstm.executeQuery();
			if (rs.next())
				return lastPostId = Integer.parseInt(rs.getString("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastPostId;
	}

	// return last id of tag
	public int lastTag(Connection conn) throws SQLException {
		String sql = "SELECT id from tag_tb order by id desc limit 1";
		PreparedStatement pstm = conn.prepareStatement(sql);
		int lastTagId = 0;
		try {
			ResultSet rs = pstm.executeQuery();
			if (rs.next())
				return lastTagId = Integer.parseInt(rs.getString("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastTagId;
	}

	// Add comment
	public boolean addComment(Connection conn, int postId, int userId, String comment) throws SQLException {
		String sql = "INSERT INTO public.comment_tb(post_id, user_id, content, create_at) VALUES (?, ?, ?, ?);";
		Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, postId);
		pstm.setInt(2, userId);
		pstm.setString(3, comment);
		pstm.setTimestamp(4, date);
		System.out.println(pstm);
		int lastTagId = 0;
		try {
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
