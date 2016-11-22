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

import projectTest.example.beans.PostTb;
import projectTest.example.beans.TagTb;
import projectTest.example.conn.ConnectDB;
import projectTest.example.util.DBUtil;

public class AddPost extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tagRelationId = request.getParameter("tagRelationId");

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		DBUtil dbUtil = new DBUtil();
		ConnectDB connectDB = new ConnectDB();
		Connection connection = null;
		List<TagTb> listTag = new ArrayList<TagTb>();
		List<PostTb> listPost = new ArrayList<PostTb>();
		String nameTag = request.getParameter("tag");
		int tagRelation = Integer.parseInt(request.getParameter("tagRelation"));
		boolean result = false;
		connection = connectDB.ConnectDB();
		if (title != null && content != null) {
			try {
				result = dbUtil.addPost(connection, title, content, userId);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (result = true) {
			try {
				listTag = dbUtil.showAllTag(connection);
				listPost = dbUtil.listPost(connection, 0);
				dbUtil.addTag(connection, nameTag);
				int post_id = dbUtil.lastPost(connection);
				int tag_id = dbUtil.lastTag(connection);
				dbUtil.addRelationPostTag(connection, post_id, tag_id);
				dbUtil.addRelationPostTag(connection, post_id, tagRelation);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			connectDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("listTag", listTag);
		request.setAttribute("listPost", listPost);
		request.getRequestDispatcher("/WEB-INF/view/mainContent.jsp").forward(request, response);
	}
}
