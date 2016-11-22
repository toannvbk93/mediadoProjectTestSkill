package projectTest.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projectTest.example.beans.PostTb;
import projectTest.example.beans.TagTb;
import projectTest.example.beans.UserComment;
import projectTest.example.beans.UserTb;
import projectTest.example.conn.ConnectDB;
import projectTest.example.util.DBUtil;

public class PostDetail extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBUtil db = new DBUtil();
		UserTb user = new UserTb();
		ConnectDB connection = new ConnectDB();
		Connection conn = null;
		conn = connection.ConnectDB();
		int id;
		id = Integer.parseInt(request.getParameter("postId"));
		String comment = request.getParameter("comment");
		String user_id = request.getParameter("userCommentId");
		PostTb post = new PostTb();
		List<TagTb> listTag = new ArrayList<TagTb>();
		HttpSession httpSession = null;
		List<UserComment> listUserComment = new ArrayList<UserComment>();
		int count = 0;
		if (conn != null) {
			try {
				post = db.postDetail(conn, id);
				if (post != null) {
					user = db.findUserById(conn, post.getUserId());
					listTag = db.findTagByPostId(conn, post.getId());
					count = db.CountCommentByPostId(conn, post.getId());
					listUserComment = db.findCommentByPostId(conn, post.getId(), listUserComment);
					request.setAttribute("post", post);
					request.setAttribute("user", user);
					request.setAttribute("listTag", listTag);
					if (comment != null && user_id != null) {
						int userCommentId = Integer.parseInt(user_id);
						db.addComment(conn, id, userCommentId, comment);
						listUserComment = db.findCommentByPostId(conn, post.getId(), listUserComment);
						count = db.CountCommentByPostId(conn, post.getId());
					}
					request.setAttribute("countComment", count);
					request.setAttribute("listUserComment", listUserComment);
					request.getRequestDispatcher("/WEB-INF/view/postDetail.jsp").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			connection.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/view/mainContent.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
