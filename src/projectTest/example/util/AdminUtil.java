package projectTest.example.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import projectTest.example.beans.AdminPost;
import projectTest.example.beans.PostTb;
import projectTest.example.beans.UserComment;
import projectTest.example.beans.UserTb;
/**
 * Admin active with database
 *
 * @author ToanNV
 *
 */
public class AdminUtil {
	public AdminUtil() {
	}

	// Admin login
	public UserTb adminLogin(Connection conn, String email, String password) throws SQLException, ParseException {
		String sql = "Select a.id, a.email, a.password, a.username, a.create_at from user_tb a where a.email = ? and a.password= ? and active=true and role=1";
		PreparedStatement pstm = conn.prepareStatement(sql);
		Encode encode = new Encode();
		String password_ = encode.getMD5(encode.getMD5(password.trim()));
		pstm.setString(1, email);
		pstm.setString(2, password_);
		System.out.println(pstm);
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

	// Admin select all user
	public List<UserTb> selectAllUser(Connection conn) throws SQLException, ParseException {
		String sql = "select * FROM user_tb order by id;";
		PreparedStatement pstm = conn.prepareStatement(sql);
		List<UserTb> listUser = new ArrayList<>();
		System.out.println(pstm);
		try {
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				UserTb user = new UserTb();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				listUser.add(user);
			}
			return listUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Admin delete user
	public boolean adminDeleteUser(Connection conn, int userId) throws SQLException, ParseException {
		String sql = "DELETE FROM user_tb WHERE user_tb.id = ? ;";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, userId);
		System.out.println(pstm);
		try {
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Admin edit user
	public void adminEditUser(Connection conn, int userId, String username, String email, String password)
			throws SQLException, ParseException {
		String sql = "UPDATE user_tb SET username=?, email=?, password=? update_at=? WHERE id=?;";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, userId);
		System.out.println(pstm);
		try {
			pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Admin find user by post Id
	public UserTb findUserByPostId(Connection conn, int postId) throws SQLException, ParseException {
		String sql = "select user_tb.id, user_tb.username from user_tb, post_tb where post_tb.id= ? and user_tb.id=post_tb.user_id;";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, postId);
		UserTb user = null;
		System.out.println(pstm);
		try {
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("user_tb.id"));
				user.setUsername(rs.getString("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	// admin show post detail
	public List<AdminPost> showAllPostDetail(Connection conn) throws SQLException, ParseException {
		DBUtil db = new DBUtil();
		String sql = "select * from post_tb order by id";
		PreparedStatement pstm = conn.prepareStatement(sql);
		List<AdminPost> listPostDetail = new ArrayList<AdminPost>();
		System.out.println(pstm);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			PostTb post = new PostTb();
			UserTb user = new UserTb();
			AdminPost adminPost = new AdminPost();
			UserComment userComment = new UserComment();
			List<UserComment> listUserComment = new ArrayList<UserComment>();
			post.setId(rs.getInt("id"));
			post.setTitle(rs.getString("title"));
			post.setContent(rs.getString("content"));
			post.setCreateAt(rs.getDate("create_at"));
			user = findUserByPostId(conn, rs.getInt("id"));
			listUserComment = db.findCommentByPostId(conn, rs.getInt("id"), listUserComment);
			adminPost.AdminPost(post, user, listUserComment);
			listPostDetail.add(adminPost);
		}
		return listPostDetail;
	}
}
